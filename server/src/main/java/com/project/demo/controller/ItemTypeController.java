package com.project.demo.controller;

import com.project.demo.entity.ItemType;
import com.project.demo.service.ItemTypeService;
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
 * 物品类型：(ItemType)表控制层
 *
 */
@RestController
@RequestMapping("/item_type")
public class ItemTypeController extends BaseController<ItemType, ItemTypeService> {

    /**
     * 物品类型对象
     */
    @Autowired
    public ItemTypeController(ItemTypeService service) {
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
