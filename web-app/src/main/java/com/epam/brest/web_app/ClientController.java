package com.epam.brest.web_app;

import com.epam.brest.Client;
import com.epam.brest.ClientDtoService;

import com.epam.brest.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private final ClientDtoService clientDtoService;

    private final ClientService clientService;

    public ClientController(ClientDtoService clientDtoService,
                            ClientService clientService) {
        this.clientDtoService = clientDtoService;
        this.clientService = clientService;
    }

    /**
     * Goto clients list page.
     *
     * @return view name
     */
    @GetMapping(value = "/clients")
    public final String clients(Model model) {
        model.addAttribute("clients", clientDtoService.findAllWithAvgSBalance());
        return "clients";
    }

    /**
     * Go to edit client page.
     *
     * @return view name
     */
    @GetMapping(value = "/client/{id}")
    public final String gotoEditClientPage(@PathVariable Integer id, Model model) {
        logger.debug("gotoEditClientPage(id:{},model:{})", id, model);
        model.addAttribute("isNew", false);
        model.addAttribute("department", clientService.getClientById(id));
        return "department";
    }

    /**
     * Goto new department page.
     *
     * @return view name
     */
    @GetMapping(value = "/client")
    public final String gotoAddClientPage(Model model) {
        logger.debug("gotoAddClientPage({})", model);
        model.addAttribute("isNew", true);
        model.addAttribute("client", new Client());
        return "client";
    }

    /**
     * Persist new client into persistence storage.
     *
     * @param client new client with filled data.
     * @return view name
     */
    @PostMapping(value = "/client")
    public String addDepartment(Client client) {

        logger.debug("addClient({}, {})", client);
        this.clientService.create(client);
        return "redirect:/clients";
    }

    /**
     * Update client.
     *
     * @param client client with filled data.
     * @return view name
     */
    @PostMapping(value = "/department/{id}")
    public String updateClient(Client client) {

        logger.debug("updateClient({}, {})", client);
        this.clientService.update(client);
        return "redirect:/clients";
    }

    /**
     * Delete client.
     *
     * @return view name
     */
    @GetMapping(value = "/client/{id}/delete")
    public final String deleteClientById(@PathVariable Integer id, Model model) {

        logger.debug("delete({},{})", id, model);
        clientService.delete(id);
        return "redirect:/clients";
    }


}
