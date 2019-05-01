package com.superstudentregion.AOP;

import com.github.pagehelper.PageHelper;
import com.superstudentregion.bean.PageBean;
import org.apache.ibatis.javassist.ClassClassPath;
import org.apache.ibatis.javassist.ClassPool;
import org.apache.ibatis.javassist.CtClass;
import org.apache.ibatis.javassist.CtMethod;
import org.apache.ibatis.javassist.bytecode.CodeAttribute;
import org.apache.ibatis.javassist.bytecode.LocalVariableAttribute;
import org.apache.ibatis.javassist.bytecode.MethodInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class PageHelperAop {
    private static final Logger log = LoggerFactory.getLogger(PageHelperAop.class);

    private static final ThreadLocal<PageBean> pageBeanContext = new ThreadLocal();
    @Before(value = "execution(public * com.superstudentregion.controller.*.all*(..))")
    public void controllerAop(JoinPoint joinPoint) throws Exception {
        log.info("Controller正在执行PageHelperAop");
        PageBean pageBean =null;

        Object[] args = joinPoint.getArgs();
        //获取类名
        String clazzName = joinPoint.getTarget().getClass().getName();
        //获取方法名称
        String methodName = joinPoint.getSignature().getName();
        //通过反射获取参数列表
        Map<String,Object > nameAndArgs = this.getFieldsName(this.getClass(), clazzName, methodName,args);

        pageBean = (PageBean) nameAndArgs.get("pageBean");
        if(pageBean == null){
            pageBean = new PageBean();
            pageBean.setCurrentPage((Integer) nameAndArgs.get("currentPage"));
            pageBean.setPageSize((Integer) nameAndArgs.get("pageSize"));
        }
        //将分页参数放置线程变量中
        pageBeanContext.set(pageBean);
    }

    @Before(value = "execution(public * com.superstudentregion.service.impl.*.all*(..))")
    public void serviceImplAop(){
        log.info("Impl正在执行PageHelperAop");
        PageBean pageBean = pageBeanContext.get();
        PageHelper.startPage(pageBean.getCurrentPage(), pageBean.getPageSize());
    }

    @AfterReturning(value = "execution(* com.superstudentregion.mapper.*.all*(..))")
    public void mapperAop(){
        log.info("mapper正在执行PageHelperAop");
    }

    /**
     * 通过反射获取参数列表
     * @throws Exception
     */
    private Map<String,Object> getFieldsName(Class cls, String clazzName, String methodName, Object[] args) throws Exception {
        Map<String,Object > map=new HashMap<String,Object>();

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < cm.getParameterTypes().length; i++){
            map.put( attr.variableName(i + pos),args[i]);
        }
        return map;
    }

}
