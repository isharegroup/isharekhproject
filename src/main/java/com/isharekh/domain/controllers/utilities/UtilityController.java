package com.isharekh.domain.controllers.utilities;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
Create By: Ron Rith
Create Date: 3/1/2018
*/
@Controller
@RequestMapping(value = "/utilities")
public class UtilityController {
    @RequestMapping(value = "/advanced",method = RequestMethod.GET)
    private String getAdvancedForm(){
        return "/utilities/advanced";
    }

    @RequestMapping(value = "/editors",method = RequestMethod.GET)
    private String getAdvancedEditors(){
        return "/utilities/forms/editors";
    }

    @RequestMapping(value = "/general",method = RequestMethod.GET)
    private String getAdvancedGeneral(){
        return "/utilities/forms/general";
    }

    @RequestMapping(value = "/chartjs",method = RequestMethod.GET)
    private String getAdvancedChartjs(){
        return "/utilities/charts/chartjs";
    }

    @RequestMapping(value = "/flot",method = RequestMethod.GET)
    private String getAdvancedFlot(){
        return "/utilities/charts/flot";
    }

    @RequestMapping(value = "/inline",method = RequestMethod.GET)
    private String getAdvancedInline(){
        return "/utilities/charts/inline";
    }

    @RequestMapping(value = "/morris",method = RequestMethod.GET)
    private String getAdvancedMorris(){
        return "/utilities/charts/morris";
    }

    @RequestMapping(value = "/invoice",method = RequestMethod.GET)
    private String getAdvancedInvoice(){
        return "/utilities/examples/invoice";
    }

    @RequestMapping(value = "/in_print",method = RequestMethod.GET)
    private String getAdvancedInvoicePrint(){
        return "/utilities/examples/invoice-print";
    }

    @RequestMapping(value = "/lockscreen",method = RequestMethod.GET)
    private String getAdvancedLockScreen(){
        return "/utilities/examples/lockscreen";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    private String getAdvancedLogin(){
        return "/utilities/examples/login";
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    private String getAdvancedProfile(){
        return "/utilities/examples/profile";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    private String todoRegister() {
        return "/utilities/examples/register";
    }

    @RequestMapping(value = "/pace", method = RequestMethod.GET)
    private String todoPace() {
        return "/utilities/examples/pace";
    }

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    private String todo404() {
        return "/utilities/examples/404";
    }

    @RequestMapping(value = "/500", method = RequestMethod.GET)
    private String todo500() {
        return "/utilities/examples/500";
    }

    @RequestMapping(value = "/blank", method = RequestMethod.GET)
    private String todoBlank() {
        return "/utilities/examples/blank";
    }

    @RequestMapping(value = "/boxed", method = RequestMethod.GET)
    private String todoBoxed() {
        return "/utilities/layout/boxed";
    }

    @RequestMapping(value = "/collapsed_sidebar", method = RequestMethod.GET)
    private String todoCollapsedSidebar() {
        return "/utilities/layout/collapsed-sidebar";
    }

    @RequestMapping(value = "/fixed", method = RequestMethod.GET)
    private String todoFixed() {
        return "/utilities/layout/fixed";
    }

    @RequestMapping(value = "/top_nav", method = RequestMethod.GET)
    private String todoTopNav() {
        return "/utilities/layout/top-nav";
    }

    @RequestMapping(value = "/compose", method = RequestMethod.GET)
    private String todoCompose() {
        return "/utilities/mailbox/compose";
    }

    @RequestMapping(value = "/mailbox", method = RequestMethod.GET)
    private String todoMailBox() {
        return "/utilities/mailbox/mailbox";
    }

    @RequestMapping(value = "/read_mail", method = RequestMethod.GET)
    private String todoRealMail() {
        return "/utilities/mailbox/read_mail";
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    private String todoData() {
        return "/utilities/tables/data";
    }

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    private String todoSimple() {
        return "/utilities/tables/simple";
    }

    @RequestMapping(value = "/buttons", method = RequestMethod.GET)
    private String todoButton() {
        return "/utilities/UI/buttons";
    }

    @RequestMapping(value = "/ui/general", method = RequestMethod.GET)
    private String todoGeneral() {
        return "/utilities/UI/general";
    }

    @RequestMapping(value = "/ui/icons", method = RequestMethod.GET)
    private String todoICons() {
        return "/utilities/UI/icons";
    }

    @RequestMapping(value = "/ui/modals", method = RequestMethod.GET)
    private String todoModals() {
        return "/utilities/UI/modals";
    }

    @RequestMapping(value = "/ui/sliders", method = RequestMethod.GET)
    private String todoSliders() {
        return "/utilities/UI/sliders";
    }

    @RequestMapping(value = "/ui/timeline", method = RequestMethod.GET)
    private String todoTimeline() {
        return "/utilities/UI/timeline";
    }

    @RequestMapping(value = "/ui/calendar", method = RequestMethod.GET)
    private String todoCalendar() {
        return "/utilities/UI/calendar";
    }

    @RequestMapping(value = "/ui/widgets", method = RequestMethod.GET)
    private String todoWidgets() {
        return "/utilities/UI/widgets";
    }

    @RequestMapping(value = "/ui/starter", method = RequestMethod.GET)
    private String todoStarter() {
        return "/utilities/UI/starter";
    }

    @RequestMapping(value = "/ui/index2", method = RequestMethod.GET)
    private String todoIndex2() {
        return "/utilities/UI/index2";
    }
    @RequestMapping(value = "/ui/geditor", method = RequestMethod.GET)
    private String todoGEditor() {
        return "/utilities/editors/editor";
    }
}
