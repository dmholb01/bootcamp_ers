package com.capital.one.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.capital.one.datamodelbeans.Reimbursement;
import com.capital.one.datamodelbeans.Users;
import com.capital.one.services.ReimbursementService;

public class ReimbursementController {
    private Logger log = Logger.getLogger("ReimbursementController");

    private ReimbursementService rs = new ReimbursementService();

    Users myUser = new Users();

    Reimbursement tempReimbursement = new Reimbursement();

    public void processGetRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("ReimbursementController processing get request");
        // I'm not populating a list here like he was...could do later but for now, nothing I want to do on get request.

        String requestUrl = req.getRequestURI().substring(req.getContextPath().length());

        switch (requestUrl) {

            case "/static/reimbursements/MyPending":
                rs.myPendingReimbursements(req, resp);
                resp.sendRedirect("/ers_project/static/DisplayReimbursements.html");
                break;
            case "/static/reimbursements/MyPast":
                rs.myPastReimbursements(req, resp);
                resp.sendRedirect("/ers_project/static/DisplayReimbursements.html");
                break;
            case "/static/reimbursements/AllPending":
                try {
                    if ((int) req.getSession().getAttribute("currentRoleId") == 2) {
                        rs.allPendingReimbursements(req, resp);
                        resp.sendRedirect("/ers_project/static/DisplayReimbursements.html");
                    }
                    else {
                        req.getRequestDispatcher("/static/NotAuthorized.html").forward(req, resp);
                    }
                }
                catch (Exception e) {
                    log.error("Page did not exist");
                }
                break;
            case "/static/reimbursements/AllPast":
                try {
                    if ((int) req.getSession().getAttribute("currentRoleId") == 2) {
                        rs.allPastReimbursements(req, resp);
                        resp.sendRedirect("/ers_project/static/DisplayReimbursements.html");
                    }
                    else {
                        req.getRequestDispatcher("/static/NotAuthorized.html").forward(req, resp);
                    }
                }
                catch (Exception e) {
                    log.error("Page did not exist");
                }
                break;
            case "/static/reimbursements/MySearch":
                try {
                    if ((int) req.getSession().getAttribute("currentRoleId") == 2) {
                        req.getRequestDispatcher("/static/FinanceManagerSearch.html").forward(req, resp);
                    }
                    else {
                        req.getRequestDispatcher("/static/EmployeeSearch.html").forward(req, resp);
                    }
                }
                catch (Exception e) {
                    log.error("Page did not exist");
                }
                break;
            case "/static/reimbursements/MyNew":
                resp.sendRedirect("/ers_project/static/EmployeeNewReimbursement.html");
                break;
            case "/static/reimbursements/OtherNew":
                resp.sendRedirect("/ers_project/static/OtherNewReimbursement.html");
                break;
            case "/static/reimbursements/MyDelete":
                resp.sendRedirect("/ers_project/static/EmployeeDeleteReimbursement.html");
                break;
            case "/static/reimbursements/OtherDelete":
                resp.sendRedirect("/ers_project/static/OtherDeleteReimbursement.html");
                break;
            case "/static/reimbursements/StatusNew":
                // TODO: call rs for getting List of Reimbursements but filter by Pending Last 10 days
                resp.sendRedirect("/ers_project/static/StatusReimbursements.html");
                break;
            case "/static/reimbursements/StatusAll":
                // TODO: call rs for getting List of Reimbursements but filter by Pending
                resp.sendRedirect("/ers_project/static/StatusReimbursements.html");
                break;

            // /users/{id}
            default:
                // User user = us.findById(requestUrl.substring(7), resp);
                log.trace("requestURL: " + requestUrl);
                break;
        }
    }

    public void processPostRequests(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("UserController processing post request.");
        String requestUrl = req.getRequestURI().substring(req.getContextPath().length());

        switch (requestUrl) {
            case "/users/login":
                // login(req, resp);
                break;

            default:
                break;
        }
    }

}