package cn.lili.modules.member.service;

import cn.lili.modules.member.entity.dos.MemberGroupUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

public interface MemberGroupUserService extends IService<MemberGroupUser> {
    boolean updateGroupUsers(String groupId, List<String> memberIds);
    boolean updateUserGroups(String memberId, List<String> groupIds);
}
