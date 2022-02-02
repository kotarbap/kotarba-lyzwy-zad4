package pl.edu.wszib.kotarba.ice.skates.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.kotarba.ice.skates.service.ICartService;
import pl.edu.wszib.kotarba.ice.skates.session.SessionObject;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

    @Autowired
    ICartService cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/add/{skatesId}")
    public String addSkatesToCart(@PathVariable Integer skatesId) {
        this.cartService.addSkatesToCart(skatesId);
        return "redirect:/main";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String cart(Model model) {
        model.addAttribute("cart",
                this.sessionObject.getCart());
        model.addAttribute("sum", this.sessionObject.getCart().getSum());
        model.addAttribute("logged", this.sessionObject.isLogged());

        return "cart";
    }
}
