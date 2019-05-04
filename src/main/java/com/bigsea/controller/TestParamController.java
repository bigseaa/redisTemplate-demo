package com.bigsea.controller;

import com.bigsea.entity.FamilyMember;
import com.bigsea.entity.Player;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = {"/testParam"})
public class TestParamController {

    @RequestMapping(value = {"/func.action"}, method = RequestMethod.POST)
    public @ResponseBody List<FamilyMember> test(@RequestBody Player player) {
        return player.getFamilyMemberList();
    }

}
