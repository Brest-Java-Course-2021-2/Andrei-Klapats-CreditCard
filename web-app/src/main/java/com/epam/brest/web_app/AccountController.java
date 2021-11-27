package com.epam.brest.web_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AccountController {

    /**
     * Goto account list page.
     *
     * @return view name
     */
    @GetMapping(value = "/accounts")
    public final String account(Model model) {
        return "accounts";
    }

    /**
     * Goto edit account page.
     *
     * @return view name
     */
    @GetMapping(value = "/account/{id}")
    public final String gotoEditAccountPage(@PathVariable Integer id, Model model) {
        return "account";
    }

    /**
     * Goto new account page.
     *
     * @return view name
     */
    @GetMapping(value = "/account/add")
    public final String gotoAddAccountPage(Model model) {
        return "account";
    }
}
