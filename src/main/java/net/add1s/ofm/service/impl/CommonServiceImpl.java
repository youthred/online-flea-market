package net.add1s.ofm.service.impl;

import net.add1s.ofm.config.props.AppProps;
import net.add1s.ofm.pojo.vo.FooterVO;
import net.add1s.ofm.service.ICommonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class CommonServiceImpl implements ICommonService {

    private final AppProps appProps;

    public CommonServiceImpl(AppProps appProps) {
        this.appProps = appProps;
    }

    @Override
    public FooterVO appInfo() {
        return new FooterVO()
                .setJavaVersion(System.getProperty("java.version"))
                .setProjectVersion(appProps.getProjectVersion())
                .setProjectName(appProps.getProjectName())
                .setProjectDescription(appProps.getProjectDescription())
                .setGithub(appProps.getGithub())
                ;
    }
}
