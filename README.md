# 在线跳蚤市场

> 可在线私聊的WEB跳蚤市场

## 使用技术

- 架构：SpringBoot
- 技术点：SpringBoot, SpringSecurity, MyBatisPlus, Netty
- 后台语言：Java_11.0.8
- 前台语言：HTML, JavaScript, VUE
- 数据库：MySQL_8.0.22
- IDE：IntelliJ IDEA 2021.1 (Ultimate Edition)
- 数据库操作工具：Navicat Premium 15
- 测试服务器：CentOS_7

## 模块及功能点

- 登录
    - [x] Session/Cookie
    - [x] JSON AJAX响应
- 用户前台
    - 用户主页
        - [x] 我发布的
        - [x] 我卖出的
        - [x] 我买到的
        - [x] 我的私聊
        - [ ] 我的点赞
        - [ ] 我的评论
    - 首页
        - [x] 全局搜索（商品标题、描述）
        - [x] 商品分类分页
    - 商品详情页
        - [x] 商品展示
        - [x] 举报
        - [x] “我想要”私聊（Netty）
        - [ ] 点赞
        - [ ] 评论
- 后台管理
    - 商品管理
        - [x] 举报审核
        - [x] 商品分类
    - 用户管理
        - 用户
            - [x] 启用/禁用
            - [x] 角色绑定
    - 系统权限管理
        - 角色
            - [x] 权限绑定
            - [ ] 角色增删改
        - [x] 接口权限
            
