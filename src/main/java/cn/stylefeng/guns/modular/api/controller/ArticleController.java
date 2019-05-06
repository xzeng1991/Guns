/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.stylefeng.guns.modular.api.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.stylefeng.guns.core.common.annotion.BussinessLog;
import cn.stylefeng.guns.core.common.annotion.Permission;
import cn.stylefeng.guns.core.common.constant.Const;
import cn.stylefeng.guns.core.common.constant.dictmap.DeleteDict;
import cn.stylefeng.guns.core.common.constant.dictmap.RoleDict;
import cn.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import cn.stylefeng.guns.core.common.exception.BizExceptionEnum;
import cn.stylefeng.guns.core.common.node.ZTreeNode;
import cn.stylefeng.guns.core.common.page.LayuiPageFactory;
import cn.stylefeng.guns.core.log.LogObjectHolder;
import cn.stylefeng.guns.modular.api.service.ArticleService;
import cn.stylefeng.guns.modular.api.warpper.ArticleWrapper;
import cn.stylefeng.guns.modular.system.entity.Role;
import cn.stylefeng.guns.modular.system.entity.User;
import cn.stylefeng.guns.modular.system.model.RoleDto;
import cn.stylefeng.guns.modular.system.service.RoleService;
import cn.stylefeng.guns.modular.system.service.UserService;
import cn.stylefeng.guns.modular.system.warpper.RoleWrapper;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ResponseData;
import cn.stylefeng.roses.core.util.ToolUtil;
import cn.stylefeng.roses.kernel.model.exception.ServiceException;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 文章管理制器
 *
 * @author sudong
 * @Date 2019年5月6日22:09:14
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {

    private static String PREFIX = "/modular/smallProgram/article/";

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    /**
     * 跳转到文章列表页面
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping("")
    public String index() {

        return PREFIX + "/article.html";
    }

    /**
     * 跳转到添加角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:30 PM
     */
    @RequestMapping(value = "/article_add")
    public String roleAdd() {
        return PREFIX + "/article_add.html";
    }

    /**
     * 跳转到修改角色
     *
     * @author fengshuonan
     * @Date 2018/12/23 6:31 PM
     */
    @Permission
    @RequestMapping(value = "/article_edit")
    public String roleEdit(@RequestParam Long roleId) {
        return PREFIX + "/article_edit.html";
    }



    /**
     * 获取文章列表
     *
     * @author fengshuonan
     * @Date 2019/05/06 6:31 PM
     */
    //@Permission
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(value = "title", required = false) String title) {
        Page<Map<String, Object>> articles = articleService.selectArticles(title);
        Page<Map<String, Object>> wrap = new ArticleWrapper(articles).wrap();
        return LayuiPageFactory.createPageInfo(wrap);
    }



}
