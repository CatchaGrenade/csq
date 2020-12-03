package com.learn.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.comUtil.CommUtil;
import com.learn.model.UserModel;
import com.learn.protocol.error.ErrorCode;
import com.learn.protocol.exception.DefinedException;
import com.learn.protocol.response.ApiResponse;
import com.learn.services.UserSv;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.learn.comUtil.CommUtil.getPageNum;
import static com.learn.comUtil.CommUtil.getPageSize;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserSv userSv;

    @GetMapping("/getAllUser")
    @ResponseBody
    public ResponseEntity<ApiResponse<List<UserModel>>> getAllUser(HttpServletRequest request, @RequestParam(value="page") int pageNum, @RequestParam(value="limit") int limit) {
        Map<String, Object> params = CommUtil.requestParamToMap4Miniui(request);
        PageHelper.startPage(getPageNum(params),getPageSize(params));
        List<UserModel> userModelList = userSv.getAllUser(params);
        PageInfo pageInfo = new PageInfo(userModelList);
        return ResponseEntity.ok(new ApiResponse<List<UserModel>>(pageInfo.getList(),pageInfo.getTotal()));
    }

    @GetMapping("/getUserByAccount/{account}")
    @ResponseBody
    public ResponseEntity<ApiResponse<UserModel>> getUserByAccount(@PathVariable("account") String account) {
        UserModel userModel = userSv.selectByKey(account);
        return ResponseEntity.ok(new ApiResponse<UserModel>(userModel));
    }

    @PostMapping(value = "/addOrUpdUser", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ApiResponse> addOrUpdUser(@RequestBody UserModel userModel) {
        if(userModel.getAccount() == null||"".equals(userModel.getAccount())){
            throw new DefinedException(ErrorCode.BAD_REQUEST);
        }
        UserModel isHave = userSv.selectByKey(userModel.getAccount());
        int i = 0 ;
        if(isHave!=null){
            i = userSv.updateNotNull(userModel);
        }else{
            i = userSv.insertSelective(userModel);
        }
        return ResponseEntity.ok(new ApiResponse(ErrorCode.NO_ERROR,i));
    }

    @DeleteMapping("/delUserByAccount/{accountStr}")
    public ResponseEntity<ApiResponse> delUserByAccount(@PathVariable("accountStr") String accountStr) {
        String[] ids = accountStr.split(",");
        List<String> idList= Arrays.asList(ids);
        userSv.deleteByIds(idList,UserModel.class,"account");
        return ResponseEntity.ok(new ApiResponse(ErrorCode.NO_ERROR));
    }
}
