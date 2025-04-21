package com.shopstore.store.web;

import com.shopstore.store.domain.Customer;
import com.shopstore.store.domain.Goods;
import com.shopstore.store.serivce.*;

import com.shopstore.store.serivce.imp.CustomerServiceImp;
import com.shopstore.store.serivce.imp.GoodsServiceImp;
import com.shopstore.store.serivce.imp.OrderServiceImp;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.text.*;
import java.util.*;

//在XML文件中配置Controller
//@WebServlet(name = "Controller", value = "/Controller")
public class Controller extends HttpServlet
{
    private DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    private CustomerService customerService = new CustomerServiceImp();
    private GoodsService goodsService = new GoodsServiceImp();
    private OrderService orderService = new OrderServiceImp();

    private int totalPage=0; //總頁數
    private int pageSize=10; //每頁筆數
    private int currentPage= 1;


    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        pageSize= Integer.parseInt(config.getInitParameter("pageSize"));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");


        //使用者註冊
        if("register".equals(action))
        {
            String userid = request.getParameter("userid");
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String address = request.getParameter("address");
            String birthday = request.getParameter("birthday");
            String phone = request.getParameter("phone");

            //在服務器裡再驗證一次資料
            List<String> error = new ArrayList<String>();
            if(userid.equals("")||userid==null)
            {
                error.add("帳號不能為空");
            }
            if(password.equals("")||password==null)
            {
                error.add("密碼不能為空");
            }
            if(name.equals("")||name==null)
            {
                error.add("姓名不能為空");
            }
            if(!password.equals(password2))
            {
                error.add("兩次輸入的密碼不同");
            }
            String date ="^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
            if(!birthday.matches(date))
            {
                error.add("日期格式錯誤");
            }
            if(error.size()>0)
            {
                request.setAttribute("error", error);
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
            else
            {
                Customer customer = new Customer();
                customer.setId(userid);
                customer.setName(name);
                customer.setAddress(address);
                customer.setPhone(phone);
                customer.setPassword(password);
                try
                {
                    java.util.Date d = dateformat.parse(birthday);
                    java.sql.Date sqlDate = new java.sql.Date(d.getTime());
                    customer.setBirthday(sqlDate);
                }
                catch(ParseException e)
                {
                    e.printStackTrace();
                }

                //插入到數據庫
                try
                {
                    //註冊成功
                    customerService.register(customer);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                catch(ServiceException e)
                {
                    //如果有異常代表使用者Id已存在
                    error.add("ID已存在");
                    request.setAttribute("error",error);
                    request.getRequestDispatcher("/register.jsp").forward(request, response);

                }

            }
        }
        else if("login".equals(action))
        {
            //取參數
            String userid =request.getParameter("userid");
            String password =request.getParameter("password");

            Customer customer = new Customer();
            customer.setId(userid);
            customer.setPassword(password);

            //登入成功
            if(customerService.login(customer))
            {
                HttpSession session = request.getSession();
                session.setAttribute("customer",customer);
                response.sendRedirect("Controller?action=list");
            }
            else //登入失敗
            {
                List<String> errors = new ArrayList<String>();
                errors.add("帳號密碼不正確，請重新輸入");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }

        }
        else if("list".equals(action))
        {
            //商品列表
            List<Goods> goodsList = goodsService.queryAll();

            //每10筆跳到下一頁
            if(goodsList.size() % pageSize ==0)
            {
                totalPage = goodsList.size() / pageSize;
            }
            else
            {
                totalPage = goodsList.size() / pageSize + 1;
            }

            request.setAttribute("totalPage",totalPage);
            request.setAttribute("currentPage",currentPage);

            int start =(currentPage-1)*pageSize;
            int end =currentPage*pageSize;

            //最後一頁
            if(currentPage==totalPage)
            {
                end =goodsList.size();
            }

            //因為分頁只顯示10筆資料，所以不用傳回全部的資料
            request.setAttribute("goodsList", goodsList.subList(start,end));
            request.getRequestDispatcher("/page.jsp").forward(request, response);
        }
        else if("paging".equals(action))
        {
            String page=request.getParameter("page");

            //上一頁
            if(page.equals("prev"))
            {
                currentPage--;
                if(currentPage<1)
                {
                    currentPage=1;
                }
            }
            //下一頁
            else if(page.equals("next"))
            {
                currentPage++;
                if(currentPage>totalPage)
                {
                    currentPage=totalPage;
                }
            }
            else
            {
                currentPage = Integer.parseInt(page);
            }
            int start = (currentPage-1)*pageSize;
            int end = currentPage * pageSize;

            List<Goods> goodsList = goodsService.queryByStartEnd(start, end);
            request.setAttribute("totalPage",totalPage);
            request.setAttribute("currentPage",currentPage);
            request.setAttribute("goodsList",goodsList);
            request.getRequestDispatcher("/page.jsp").forward(request, response);

        }
        else if("detail".equals(action))
        {
            String goodsid = request.getParameter("id");
            Goods goods = goodsService.queryDetails(Integer.parseInt(goodsid));

            request.setAttribute("goods",goods);
            request.getRequestDispatcher("/goodsdetail.jsp").forward(request, response);
        }
        else if("add".equals(action))
        {
            Long goodsid = Long.parseLong(request.getParameter("id"));
            String goodsName= request.getParameter("name");
            Double goodsPrice = Double.parseDouble(request.getParameter("price"));

            List<Map<String,Object>> cart = (List<Map<String,Object>>)request.getSession().getAttribute("cart");

            //第一次購物車裡沒有數據
            if(cart==null)
            {
                cart = new ArrayList<Map<String,Object>>();
                request.getSession().setAttribute("cart",cart);
            }

            //設一個標誌
            int flag=0;

            //輪巡購物車，看有沒有一樣的商品，有的話，數量加1
            for(Map<String,Object> item: cart)
            {
                Long goodsId2 = (Long)item.get("goodsid");
                if(goodsid.equals(goodsId2))
                {
                    Integer quantity = (Integer)item.get("quantity");
                    quantity++;
                    item.put("quantity",quantity);
                    flag++;
                }
            }

            //如果購物車沒有選中的該商品
            if(flag==0)
            {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("goodsid", goodsid);
                item.put("goodsName", goodsName);
                item.put("quantity", 1);
                item.put("price", goodsPrice);

                cart.add(item);
            }
            System.out.println(cart);

            //判斷是從哪個頁面加入購物車的
            String pagename = request.getParameter("pagename");
            //如果是從Page.jsp按加入購物車，加入後回到page.jsp
            if(pagename.equals("Frompage"))
            {
                int start =(currentPage-1)*pageSize;
                int end = currentPage * pageSize;

                List<Goods> goodsList = goodsService.queryByStartEnd(start, end);
                request.setAttribute("totalPage",totalPage);
                request.setAttribute("currentPage",currentPage);
                request.setAttribute("goodsList",goodsList);
                request.getRequestDispatcher("/page.jsp").forward(request,response);
            }
            //如果是從goodsdetail.jsp按加入購物車的話，加入後回到goodsdetail.jsp
            else if(pagename.equals("Fromdetail"))
            {
                Goods goods = goodsService.queryDetails((Long)(goodsid));
                request.setAttribute("goods",goods);
                request.getRequestDispatcher("/goodsdetail.jsp").forward(request,response);
            }

        }
        else if("cart".equals(action))
        {
            List<Map<String,Object>> cart =(List<Map<String,Object>>)request.getSession().getAttribute("cart");

            double total=0.0;

            if(cart!=null)
            {
                for(Map<String,Object> item : cart)
                {
                    Long goodsid= (Long)item.get("goodsid");
                    Integer quantity = (Integer)item.get("quantity");
                    Double price = (Double) item.get("price");
                    double subtotal = price * quantity;
                    total+=subtotal;
                }

            }
            request.setAttribute("total",total);
            request.getRequestDispatcher("/cart.jsp").forward(request,response);


        }
        else if("submitOrder".equals(action))
        {
            //送出訂單
            List<Map<String, Object>> cart = (List<Map<String, Object>>) request.getSession().getAttribute("cart");



                for (Map<String, Object> item : cart) {

                    Long goodsid = ((Number) item.get("goodsid")).longValue();
                    String strquantity = request.getParameter("quantityItem" + goodsid);
                    int quantity = 0;


                    try {
                        quantity = Integer.parseInt(strquantity);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    item.put("goodsid", goodsid);
                    item.put("quantity", quantity);

                }

            System.out.println(cart);

            String orderId=orderService.submitOrder(cart);
            request.setAttribute("orderid",orderId);
            request.getRequestDispatcher("/orderfinish.jsp").forward(request,response);
            request.getSession().removeAttribute("cart");
        }
        else if("index".equals(action))
        {
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else if("logout".equals(action))
        {
            request.getSession().removeAttribute("cart");
            request.getSession().removeAttribute("customer");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        else if("registerInit".equals(action))
        {
            //初始化，從註冊畫面進入的話:
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);

    }
}
