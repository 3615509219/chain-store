package com.lq.controller;

import com.lq.dao.NfcNumberTbMapper;
import com.lq.dao.NfcTbMapper;
import com.lq.pojo.NfcNumberTb;
import com.lq.pojo.NfcTb;
import com.lq.service.NfcTbService;
import com.lq.utils.DealFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("NFC")
public class NFC {
    @Resource
    private DealFile dealFile;
    @Resource
    private NfcTbService nfcTbService;
    @Resource
    private NfcTbMapper nfcTbMapper;
    @Resource
    private NfcNumberTbMapper nfcNumberTbMapper;


    @RequestMapping("/l")
    public String index(String url,Model model) {
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        NfcNumberTb n = new NfcNumberTb();
        NfcTb nfctb = nfcTbMapper.nfctb(url);
        NfcNumberTb nfcNumberTb = nfcNumberTbMapper.nfcNumber(nfctb.getNfcId());
        if (nfcNumberTb == null){
            n.setNfcId(nfctb.getNfcId());
            n.setNfcNumber(1);
            n.setNfcNumberTime(df.format(time));
            nfcNumberTbMapper.insert(n);
        }else{
            n.setNfcId(nfctb.getNfcId());
            n.setNfcNumber(nfcNumberTb.getNfcNumber()+1);
            n.setNfcNumberTime(df.format(time));
            nfcNumberTbMapper.insert(n);
        }
        model.addAttribute("ImgUrl",nfctb.getNfcImgUrl());
        model.addAttribute("NfcWord",nfctb.getNfcWord());
        model.addAttribute("url",url);
        model.addAttribute("corporateName",nfctb.getCorporateName());
        model.addAttribute("companySlogan",nfctb.getCompanySlogan());
        model.addAttribute("companyBackground",nfctb.getCompanyBackground());
        return "l";
    }
    @RequestMapping(value = "/loginIn",method = RequestMethod.GET)
    public String login(String nfcUrl, String nfcPwd, Model model){
        NfcTb nfc = nfcTbMapper.nfc(nfcUrl, nfcPwd);
        if(nfc==null){
//            model.addAttribute("nfc",nfc.getNfcUrl());
            model.addAttribute("null","null");
            return "error";
        }else {
            model.addAttribute("nfc",nfc.getNfcUrl());
            return "success";
        }
    }
    //????????????NFC
    @PostMapping("addNfc")
    @ResponseBody
    public int addNfc (@RequestParam(value = "file", required = false) MultipartFile multipartFile,Integer productId,String nfcPwd,String purchaseOrderProductName,@RequestParam(value = "file1", required = false) MultipartFile multipartFile1,String nfcWord,String purchaseOrderNumber,Integer accountId,@RequestParam(value = "file2", required = false) MultipartFile multipartFile2,String corporateName,String companySlogan){
        int id = 0;
        NfcTb nfcTb = new NfcTb();
        try {
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String photoPath = dealFile.getPhotoPath(multipartFile);
            String photoPath1 = dealFile.getPhotoPath(multipartFile1);
            String photoPath2 = dealFile.getPhotoPath(multipartFile2);
            nfcTb.setNfcUrl(photoPath);
            nfcTb.setProductId(productId);
            nfcTb.setNfcPwd(nfcPwd);
            nfcTb.setPurchaseOrderProductName(purchaseOrderProductName);
            nfcTb.setNfcScan("https://lianqitech.cn/NFC/l?url="+photoPath);
            nfcTb.setDate(df.format(date));
            nfcTb.setNfcImgUrl(photoPath1);
            nfcTb.setNfcWord(nfcWord);
            nfcTb.setPurchaseOrderNumber(purchaseOrderNumber);
            nfcTb.setAccountId(accountId);
            nfcTb.setCompanyBackground(photoPath2);
            nfcTb.setCorporateName(corporateName);
            nfcTb.setCompanySlogan(companySlogan);
            nfcTbMapper.insert(nfcTb);
            id = nfcTb.getNfcId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
    //?????????????????????nfc
    @GetMapping("selectAllNFC")
    @ResponseBody
    public List<NfcTb> selectAllNFC(){
       return nfcTbService.list();
    }

    //?????????????????????nfc
    @GetMapping("selectOneNFC")
    @ResponseBody
    public List<NfcTb> selectOneNFC(Integer productId){
        return nfcTbMapper.selectOneNfc(productId);
    }

    //????????????????????????????????????NFC
    @GetMapping("selectPoNFC")
    @ResponseBody
    public List<NfcTb> selectPoNFC(String purchaseOrderNumber){
        return nfcTbMapper.selectPoNFC(purchaseOrderNumber);
    }
    //??????Nfc
    @PostMapping("delNfc")
    @ResponseBody
    public void delNfc (Integer nfcId){
        nfcTbService.removeById(nfcId);

    }
    //??????Nfc?????????
    @PostMapping("updNfc")
    @ResponseBody
    public void updNfc (Integer nfcId,String nfcPwd){
        nfcTbMapper.updPwd(nfcId,nfcPwd);
    }
    //????????????nfc???????????????
    @GetMapping("selectNfcNumber")
    @ResponseBody
    public NfcNumberTb selectNfcNumber (Integer nfcId){
        NfcNumberTb nfcNumberTb = nfcNumberTbMapper.nfcNumberTb(nfcId);
        return nfcNumberTb;
    }
}
