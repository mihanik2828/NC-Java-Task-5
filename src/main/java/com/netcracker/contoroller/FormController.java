package com.netcracker.contoroller;

import com.netcracker.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class FormController {



    File file = new File("test.txt");

    @GetMapping("/user")
    public String userForm(Model model){

        model.addAttribute("file",file);
        model.addAttribute("user",new User());
        return "user";
    }

    @PostMapping("/user")
    public String addSubmit(Model model,@ModelAttribute User user, HttpServletResponse response) throws IOException {

        boolean validation=true;


        for(String str : user.toText().split("&"))
            if ("".equals(str)) validation=false;
        if(!validation)
        {
            model.addAttribute("lastAdded","You have to fill all fields");
            return "user";
        }

        validation=user.getEmail().matches(("\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}"));

        if(!validation) {
            model.addAttribute("lastAdded","Incorrect email");
            return "user";
        }

        FileReader fileReader = new FileReader(file);
        BufferedReader bf = new BufferedReader(fileReader);

        String line = bf.readLine();
        while(line!=null) {
            if(line.equals(user.toText())) {
                model.addAttribute("lastAdded", "This user added yet");
                return "user";
            }
            line = bf.readLine();
        }

        if(validation) {
            FileWriter pw = new FileWriter(file, true);
            pw.append(user.toText() + '\n');
           // System.out.println(user.toText());
            pw.close();
        }

        model.addAttribute("lastAdded","User was added");
        return "user";
    }

    @PostMapping("/byname")
    public String getByName(Model model, @ModelAttribute User user, HttpServletRequest request) throws IOException {

        FileReader fileReader = new FileReader(file);
        BufferedReader bf = new BufferedReader(fileReader);
        model.addAttribute("foundedUser","");
        model.addAttribute("useragent","");
        String line=bf.readLine();
       // System.out.println(user.getName());
        while(line!=null) {
            if(user.getName().equals(line.split("&")[0])) {
                model.addAttribute("searchResult", "I founded ");
                model.addAttribute("foundedUser", new User(line.split("&")));
                model.addAttribute("useragent",request.getHeader("User-Agent"));
                return "user";
            }
            line = bf.readLine();
        }

        model.addAttribute("searchResult","I found nothing");
        return "user";
    }

    @PostMapping("/bysurname")
    public String getBySurname(Model model,@ModelAttribute User user,HttpServletRequest request) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bf = new BufferedReader(fileReader);
        model.addAttribute("foundedUser","");
        model.addAttribute("useragent","");
        String line=bf.readLine();
       // System.out.println(user.getSurname());
        while(line!=null) {
            if(user.getSurname().equals(line.split("&")[1])) {
                model.addAttribute("searchResult", "I founded ");
                model.addAttribute("foundedUser", new User(line.split("&")));
                model.addAttribute("useragent",request.getHeader("User-Agent"));
                return "user";
            }
            line = bf.readLine();
        }

        model.addAttribute("searchResult","I found nothing");
        return "user";
    }

/*    @PostMapping("/fileload")
    public String getByName(Model model){
        file = (File) model.getAttribute("file");
        return "user";
    }*/

}

