package com.project.demo.controller;

import com.project.demo.entity.LostAndFound;
import com.project.demo.service.LostAndFoundService;
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
 * 失物招领：(LostAndFound)表控制层
 *
 */
@RestController
@RequestMapping("/lost_and_found")
public class LostAndFoundController extends BaseController<LostAndFound, LostAndFoundService> {

    /**
     * 失物招领对象
     */
    @Autowired
    public LostAndFoundController(LostAndFoundService service) {
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
