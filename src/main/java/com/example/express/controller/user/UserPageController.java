package com.example.express.controller.user;

import com.example.express.common.constant.SessionKeyConstant;
import com.example.express.controller.BaseController;
import com.example.express.domain.bean.OrderInfo;
import com.example.express.domain.bean.SysUser;
import com.example.express.domain.vo.UserInfoVO;
import com.example.express.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 普通用户页面 Controller
 * @date 2019年04月20日 23:29
 */
@Controller
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_USER')")
public class UserPageController extends BaseController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 仪表盘页面
     */
    @RequestMapping("/dashboard")
    public String showDashboardPage(@AuthenticationPrincipal SysUser sysUser, ModelMap map) {
        initModelMap(map, sysUser);
        return "user/dashboard";
    }
    /**
     * 下单页面
     */
    @RequestMapping("/order")
    public String showOrderPage(@AuthenticationPrincipal SysUser sysUser, ModelMap map) {
        initModelMap(map, sysUser);
        return "user/order";
    }

    /**
     * 支付页面
     * @author jitwxs
     * @date 2019/4/23 0:00
     */
    @RequestMapping("/order/place")
    public String placeOrder(OrderInfo orderInfo, ModelMap map, HttpSession session, @AuthenticationPrincipal SysUser sysUser) {
        initModelMap(map, sysUser);
        map.put("order", orderInfo);
        session.setAttribute(SessionKeyConstant.SESSION_LATEST_EXPRESS, orderInfo);
        return "user/payment";
    }

    /**
     * 订单列表页面
     */
    @RequestMapping("/history")
    public String showHistory(@AuthenticationPrincipal SysUser sysUser, ModelMap map) {
        initModelMap(map, sysUser);
        return "user/history";
    }

    /**
     * 个人中心页面
     */
    @RequestMapping("/info")
    public String showInfoPage(@AuthenticationPrincipal SysUser sysUser,ModelMap map) {
        initModelMap(map, sysUser);
        UserInfoVO userInfo = sysUserService.getUserInfo(sysUser);
        map.put("info", userInfo);
        return "user/info";
    }

    /**
     * 设置页面
     */
    @RequestMapping("/setting")
    public String showSettingPage(@AuthenticationPrincipal SysUser sysUser,ModelMap map) {
        initModelMap(map, sysUser);
        return "user/setting";
    }
}
