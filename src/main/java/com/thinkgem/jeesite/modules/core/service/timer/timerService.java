package com.thinkgem.jeesite.modules.core.service.timer;

import com.thinkgem.jeesite.modules.core.dao.member.MemberDao;
import com.thinkgem.jeesite.modules.core.entity.member.Member;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Lazy(false)
public class timerService {
    @Autowired
    private MemberDao memberDao;

    /*@Scheduled(cron = "0 5/1 * * * *")
    public void test(){
        System.out.println("定时任务："+new Date());
    }*/

    //注册后三个月无直推的封点
    //0 0 23/23 * * *;每天23点执行
    @Scheduled(cron = "0 0 23/23 * * *")
    @Transactional(readOnly = false)
    public void lock(){
        System.out.println("定时任务开始！！！！！");
        List<Member> memberList = memberDao.get3Month();
        if(memberList != null && memberList.size()>0){
            for (Member m:memberList) {
                String loginName = m.getLoginName();
                List<Member> ref = memberDao.isReferee(m);
                if(ref == null || ref.size()<=0){
                    User user = UserUtils.getByLoginName(loginName);
                    if("1".equals(user.getStatus())){
                        user.setStatus(0);
                        memberDao.lockOrUnlock(user);
                    }
                }
            }
        }
        System.out.println("定时任务结束！！！！！");
    }

}
