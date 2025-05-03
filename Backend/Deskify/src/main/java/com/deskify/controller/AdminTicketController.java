package com.deskify.controller;

import com.deskify.dto.CommentResponseDTO;
import com.deskify.dto.CreateAdminTicketDTO;
import com.deskify.dto.CreateTicketDTO;
import com.deskify.dto.TicketResponseDTO;
import com.deskify.dto.UpdateTicketDTO;
import com.deskify.error.UserNotFoundException;
import com.deskify.model.Status;
import com.deskify.model.Priority;
import com.deskify.model.Category;
import com.deskify.model.Ticket;
import com.deskify.model.User;
import com.deskify.repository.UserRepository;
import com.deskify.service.CategoryService;
import com.deskify.service.CommentService;
import com.deskify.service.PriorityService;
import com.deskify.service.StatusService;
import com.deskify.service.TicketService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/tickets")
public class AdminTicketController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private StatusService statusService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PriorityService priorityService;

    @GetMapping
    public String getAllTickets(Model model) {
        model.addAttribute("tickets", ticketService.getTicketList());
        return "admin/tickets";
    }

    @GetMapping("/{id}")
    public String getTicket(@PathVariable Long id, Model model) {
        TicketResponseDTO ticketDTO = ticketService.getTicketById(id);

        model.addAttribute("ticket", ticketDTO);

        List<Status> statusList = statusService.getAllStatus();
        List<Category> categoryList = categoryService.getAllCategories();
        List<Priority> priorityList = priorityService.getAllPriorities();

        model.addAttribute("status", statusList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("priorities", priorityList);

        // Devolver la vista
        return "admin/ticket-detail";
    }

    @PostMapping("/update")
    public String updateTicket(@ModelAttribute UpdateTicketDTO updateTicketDTO) {
        ticketService.updateTicket(updateTicketDTO);
        return "redirect:/admin/tickets";
    }

    @GetMapping("/new")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("createAdminTicketDTO", new CreateAdminTicketDTO());
        List<Status> statusList = statusService.getAllStatus();
        List<Category> categoryList = categoryService.getAllCategories();
        List<Priority> priorityList = priorityService.getAllPriorities();

        model.addAttribute("status", statusList);
        model.addAttribute("categories", categoryList);
        model.addAttribute("priorities", priorityList);

        return "/admin/new-ticket";
    }

    @PostMapping("/create")
    public String createTicket(@Valid @ModelAttribute CreateAdminTicketDTO createAdminTicketDTO,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "new-ticket";
        }

        // Asignar un email fijo
        String email = "elozano@deskify.com";

        createAdminTicketDTO.setEmail(email);

        ticketService.createTicket(createAdminTicketDTO);

        return "redirect:/admin/tickets";
    }

    @PostMapping("/edit")
    public String editTicket(@ModelAttribute CreateTicketDTO ticket) {
        ticketService.saveTicket(ticket);
        return "redirect:/admin/tickets";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/admin/tickets";
    }

    @PostMapping("/comments/{commentId}")
    public String updateComment(@PathVariable Long commentId,
            @RequestParam String text,
            @RequestParam Long ticketId) {
        commentService.updateComment(commentId, text);
        return "redirect:/admin/tickets/" + ticketId;
    }

}
