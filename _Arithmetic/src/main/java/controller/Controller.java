package controller;

import dao.impl.ExpressionDaoImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 戮漠
 */


@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/demo")
    static public @ResponseBody String test() {
        ExpressionDaoImpl t = new ExpressionDaoImpl();
        return "hi";
    }
}
