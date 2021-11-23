package com.epam.brest.web_app;

import com.epam.brest.ClientDtoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

//    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
//
//    private final ClientDtoService clientDtoService;
//
//    private final ClientDtoService clientService;
//
//    public ClientController(ClientDtoService clientDtoService,
//                            ClientDtoService clientService) {
//        this.clientDtoService = clientDtoService;
//        this.clientService = clientService;
//    }
//
//    /**
//     * Goto clients list page.
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/clients")
//    public final String clients(Model model) {
//        model.addAttribute("clients", clientDtoService.findAllWithAvgSBalance());
//        return "clients";
//    }
//
//    /**
//     * Goto edit client page.
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/client/{id}")
//    public final String gotoEditClientPage(@PathVariable Integer id, Model model) {
//        logger.debug("gotoEditClientPage(id:{},model:{})", id, model);
//        model.addAttribute("isNew", false);
//        model.addAttribute("department", clientService.);
//        return "department";
//    }
//
//    /**
//     * Goto new department page.
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/department")
//    public final String gotoAddDepartmentPage(Model model) {
//        logger.debug("gotoAddDepartmentPage({})", model);
//        model.addAttribute("isNew", true);
//        model.addAttribute("department", new Department());
//        return "department";
//    }
//
//    /**
//     * Persist new department into persistence storage.
//     *
//     * @param department new department with filled data.
//     * @return view name
//     */
//    @PostMapping(value = "/department")
//    public String addDepartment(Department department) {
//
//        logger.debug("addDepartment({}, {})", department);
//        this.clientService.create(department);
//        return "redirect:/departments";
//    }
//
//    /**
//     * Update department.
//     *
//     * @param department department with filled data.
//     * @return view name
//     */
//    @PostMapping(value = "/department/{id}")
//    public String updateDepartment(Department department) {
//
//        logger.debug("updateDepartment({}, {})", department);
//        this.clientService.update(department);
//        return "redirect:/departments";
//    }
//
//    /**
//     * Delete department.
//     *
//     * @return view name
//     */
//    @GetMapping(value = "/department/{id}/delete")
//    public final String deleteDepartmentById(@PathVariable Integer id, Model model) {
//
//        logger.debug("delete({},{})", id, model);
//        clientService.delete(id);
//        return "redirect:/departments";
//    }


}
