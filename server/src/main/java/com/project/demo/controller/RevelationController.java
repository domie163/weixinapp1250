package com.project.demo.controller;

import com.project.demo.entity.Revelation;
import com.project.demo.service.RevelationService;
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
 * 寻物启示：(Revelation)表控制层
 *
 */
@RestController
@RequestMapping("/revelation")
public class RevelationController extends BaseController<Revelation, RevelationService> {

    /**
     * 寻物启示对象
     */
    @Autowired
    public RevelationController(RevelationService service) {
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
