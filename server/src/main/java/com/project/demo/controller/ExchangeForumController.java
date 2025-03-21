package com.project.demo.controller;

import com.project.demo.entity.ExchangeForum;
import com.project.demo.service.ExchangeForumService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 交流论坛：(ExchangeForum)表控制层
 *
 */
@RestController
@RequestMapping("/exchange_forum")
public class ExchangeForumController extends BaseController<ExchangeForum, ExchangeForumService> {

    /**
     * 交流论坛对象
     */
    @Autowired
    public ExchangeForumController(ExchangeForumService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
