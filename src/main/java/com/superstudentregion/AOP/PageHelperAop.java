package com.superstudentregion.AOP;

import com.superstudentregion.bean.PageBean;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PageHelperAop {
    private static final Logger log = LoggerFactory.getLogger(PageHelperAop.class);

    private static final ThreadLocal<PageBean> pageBeanContext = new ThreadLocal();

}
