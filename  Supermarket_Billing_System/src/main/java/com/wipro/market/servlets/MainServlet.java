package com.wipro.market.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wipro.market.bean.MarketBillBean;
import com.wipro.market.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if (operation == null) {
            response.sendRedirect("error.html");
            return;
        }

        try {
            if ("newRecord".equals(operation)) {
                String status = addRecord(request);
                if (status == null || status.equals("FAIL")
                        || status.equals("INVALID INPUT")) {
                    response.sendRedirect("error.html");
                } else {
                    response.sendRedirect("success.html");
                }
            }
            else if ("viewRecord".equals(operation)) {
                MarketBillBean bean = viewRecord(request);
                if (bean == null) {
                    request.setAttribute("message",
                            "No matching records exists! Please try again!");
                } else {
                    request.setAttribute("bean", bean);
                }
                RequestDispatcher rd =
                        request.getRequestDispatcher("displayBill.jsp");
                rd.forward(request, response);
            }
            else if ("viewAllRecords".equals(operation)) {
                Administrator admin = new Administrator();
                request.setAttribute("billList",
                        admin.viewAllRecords());
                RequestDispatcher rd =
                        request.getRequestDispatcher("displayAllBills.jsp");
                rd.forward(request, response);
            }
            else {
                response.sendRedirect("error.html");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.html");
        }
    }

    private String addRecord(HttpServletRequest request) {
        try {
            MarketBillBean bean = new MarketBillBean();
            bean.setCustomerName(request.getParameter("customerName"));
            bean.setItemName(request.getParameter("itemName"));

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(request.getParameter("purchaseDate"));
            bean.setPurchaseDate(date);

            bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            bean.setPrice(Double.parseDouble(request.getParameter("price")));
            bean.setRemarks(request.getParameter("remarks"));

            Administrator admin = new Administrator();
            return admin.addRecord(bean);

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL";
        }
    }

    private MarketBillBean viewRecord(HttpServletRequest request) {
        try {
            String customerName = request.getParameter("customerName");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(request.getParameter("purchaseDate"));
            Administrator admin = new Administrator();
            return admin.viewRecord(customerName, date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
