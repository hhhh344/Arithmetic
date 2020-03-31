package controller;

import dao.impl.ExpressionDaoImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 戮漠
 */
public class Controller {

    @RequestMapping("/demo")
    public @ResponseBody String test(@RequestParam int n, @RequestParam int r) {
        ExpressionDaoImpl t = new ExpressionDaoImpl();
        return t.generateExpression(n, r);
    }
}
