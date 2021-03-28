package net.add1s.ofm.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import net.add1s.ofm.pojo.entity.sys.SysUser;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author pj.w@qq.com
 */
@Data
@Accessors(chain = true)
public class UserRegisterDTO implements Serializable {

    private static final long serialVersionUID = 5144249278074441072L;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String rePassword;
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    @NotBlank
    private String imageCaptcha;

    public SysUser toEntity() {
        return new SysUser()
                .setCreateTime(LocalDateTime.now())
                .setUsername(this.username)
                .setPassword(this.password)
                .setNickname(this.nickname)
                .setEmail(this.email)
                .setEnabled(false); // 新创建用户默认不启用，需验证通过后才可启用
    }
}
