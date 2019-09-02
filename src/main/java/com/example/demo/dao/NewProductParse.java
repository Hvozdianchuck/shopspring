package com.example.demo.dao;

import com.example.demo.model.Computer;
import com.example.demo.model.Product;
import com.example.demo.model.Smartphone;
import org.apache.commons.codec.binary.Base64;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@Component
public class NewProductParse{

    private int i;
   private String UPLOADED_FOLDER = "C:\\Users\\Назар\\IdeaProjects\\shopspring\\src\\main\\resources\\newproducts\\";


    private int increment(int i){
        if (i%4>=1)
            return i/4+1;
        else
            return 1;
    }
    public void save(MultipartFile file){

        System.out.println(file.getContentType());
        i++;
        String newDirectory = UPLOADED_FOLDER+"newproduct"+increment(i)+"\\";
        try {

            byte[] bytes = file.getBytes();
            if (!Files.isDirectory(Paths.get(newDirectory))){
                Files.createDirectory(Paths.get(newDirectory));}
            Path path = Paths.get(newDirectory + file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
      public   Map<String,List<String>> getImage(List<Smartphone> products){
          Map<String,List<String>> photos = new HashMap<>();
photos.put("frontImage",products.stream().map(x->Base64.encodeBase64String(x.getImage_front())).collect(Collectors.toList()));
          photos.put("backImage",products.stream().map(x->Base64.encodeBase64String(x.getImage_back())).collect(Collectors.toList()));
          photos.put("bottomImage",products.stream().map(x->Base64.encodeBase64String(x.getImage_bottom())).collect(Collectors.toList()));
          photos.put("topImage",products.stream().map(x->Base64.encodeBase64String(x.getImage_top())).collect(Collectors.toList()));
          photos.put("leftImage",products.stream().map(x->Base64.encodeBase64String(x.getImage_left_side())).collect(Collectors.toList()));
          photos.put("rightImage",products.stream().map(x->Base64.encodeBase64String(x.getImage_right_side())).collect(Collectors.toList()));
      return photos;
  }
  public String getImage(byte[] image){
        return Base64.encodeBase64String(image);
  }

}
