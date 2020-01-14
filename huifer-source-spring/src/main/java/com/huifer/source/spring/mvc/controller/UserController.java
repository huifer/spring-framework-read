//package com.huifer.source.spring.mvc.controller;
//
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.AbstractController;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
//public class UserController extends AbstractController {
////    @Override
////    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest request,
////                                      javax.servlet.http.HttpServletResponse response) throws Exception {
////        return super.handleRequest(request, response);
////    }
//
//    @Override
//    protected ModelAndView handleRequestInternal(javax.servlet.http.HttpServletRequest request,
//                                                 javax.servlet.http.HttpServletResponse response) throws Exception {
//        List<Item> itemList = new ArrayList<>();
//        itemList.add(new Item("吃的", 3.3, new Date()));
//        itemList.add(new Item("玩的", 3.3, new Date()));
//        return new ModelAndView("itemList", "itemList", itemList);
//    }
//}
