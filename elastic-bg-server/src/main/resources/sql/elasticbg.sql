/*
Navicat MySQL Data Transfer

Source Server         : loc
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : elasticbg

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2019-02-27 21:40:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for system_log
-- ----------------------------
DROP TABLE IF EXISTS `system_log`;
CREATE TABLE `system_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `insert_t` datetime DEFAULT NULL COMMENT '操作时间',
  `insert_u` varchar(50) DEFAULT NULL COMMENT '操作人员',
  `class_name` varchar(100) DEFAULT NULL COMMENT '类名（含包名）',
  `method` varchar(100) DEFAULT NULL COMMENT '执行方法',
  `type` varchar(10) DEFAULT NULL COMMENT '操作类型，自定义，以便分类',
  `ref_id` varchar(50) DEFAULT NULL COMMENT '外键ID',
  `oper` varchar(350) DEFAULT NULL COMMENT '操作内容',
  `ip_1` int(11) unsigned DEFAULT NULL COMMENT '操作IP，内网',
  `ip_2` int(11) unsigned DEFAULT NULL COMMENT '操作IP，外网',
  `city` varchar(25) DEFAULT NULL COMMENT '城市',
  `ps` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=219 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_log
-- ----------------------------
INSERT INTO `system_log` VALUES ('7', '2019-01-10 15:39:32', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('8', '2019-01-10 15:39:52', '1', 'LoginController', 'logOut', '登录日志', null, '退出系统', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('9', '2019-01-10 15:40:27', '1', 'LoginController', 'login', '登录日志', null, '登陆失败，异常：java.lang.ArithmeticException: / by zero\r\n	at com.faceghost.elasticbg.controller', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('10', '2019-01-10 15:42:11', '1', 'LoginController', 'login', '登录日志', null, '登陆失败，异常：java.lang.ArithmeticException: / by zero\r\n	at com.faceghost.elasticbg.controller', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('11', '2019-01-10 15:42:27', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('12', '2019-01-10 16:02:53', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('13', '2019-01-10 16:08:53', '1', 'LoginController', 'logOut', '登录日志', null, '退出系统', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('14', '2019-01-10 16:08:59', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('15', '2019-01-10 16:12:07', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('16', '2019-01-10 16:14:09', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('17', '2019-01-10 16:16:40', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('18', '2019-01-10 16:19:48', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('19', '2019-01-10 16:38:47', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('20', '2019-01-10 16:38:51', '1', 'SystemUserController', 'execUpdateUserInfo', '业务日志', null, '执行：个人设置', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('23', '2019-01-10 16:39:10', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', null, '执行：用户管理-更新', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('25', '2019-01-10 16:39:13', '1', 'SystemUserController', 'resetSystemUserPwd', '业务日志', null, '执行：用户管理-重置密码', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('27', '2019-01-10 16:41:25', '1', 'SystemUserController', 'execAddSystemOrg', '业务日志', null, '执行：用户管理-新增', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('31', '2019-01-10 17:17:03', '1', 'LoginController', 'login', '登录日志', null, '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('32', '2019-01-10 17:17:22', '1', 'SystemRoleController', 'execAddSystemOrg', '业务日志', null, '执行：角色管理-新增', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('33', '2019-01-10 17:17:29', '1', 'SystemRoleController', 'execAddSystemRolePerm', '业务日志', null, '执行：角色管理-配置权限', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('34', '2019-01-10 17:17:37', '1', 'SystemRoleController', 'execUpdateSystemRole', '业务日志', null, '执行：角色管理-更新', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('35', '2019-01-10 17:24:04', '1', 'SystemUserController', 'execUpdateUserInfo', '业务日志', null, '执行：个人设置', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('36', '2019-01-10 17:42:59', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('37', '2019-01-10 17:43:18', '1', 'SystemPermissionController', 'execUpdateSystemPerm', '业务日志', '29', '执行：权限配置-更新', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('38', '2019-01-10 17:47:58', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('39', '2019-01-10 17:55:18', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('40', '2019-01-10 17:55:26', '1', 'SystemPermissionController', 'execAddSystemPerm', '业务日志', '36', '执行：权限配置-新增', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('41', '2019-01-10 19:13:10', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('43', '2019-01-10 19:15:56', '1', 'SystemRoleController', 'execAddSystemRolePerm', '业务日志', '10', '执行：角色管理-权限配置', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('44', '2019-01-10 19:16:12', '1', 'SystemRoleController', 'execUpdateSystemRole', '业务日志', '10', '执行：角色管理-更新', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('45', '2019-01-10 19:16:32', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '81121', '执行：用户管理-更新', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('46', '2019-01-10 19:16:39', '1', 'SystemUserController', 'resetSystemUserPwd', '业务日志', '58131', '执行：用户管理-重置密码', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('47', '2019-01-10 19:18:16', '1', 'SystemOrgController', 'execAddSystemOrg', '业务日志', '28', '执行：组织管理-新增', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('48', '2019-01-10 19:18:26', '1', 'SystemOrgController', 'execUpdateSystemOrg', '业务日志', '28', '执行：组织管理-更新', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('49', '2019-01-11 09:25:02', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('50', '2019-01-11 09:25:36', '1', 'SystemOrgController', 'execUpdateSystemOrg', '业务日志', '9', '执行：组织管理-更新', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('51', '2019-01-11 09:46:19', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('52', '2019-01-11 09:55:54', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('53', '2019-01-11 10:17:51', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('54', '2019-01-11 11:27:34', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('55', '2019-01-11 14:24:57', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('56', '2019-01-11 14:52:03', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('57', '2019-01-11 14:52:12', '1', 'SystemUserController', 'execUpdateUserInfo', '业务日志', '1', '执行：个人设置', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('58', '2019-01-11 14:52:24', '1', 'LoginController', 'logOut', '登录日志', '1', '退出系统', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('59', '2019-01-11 14:52:34', '93141', 'LoginController', 'login', '登录日志', '93141', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('60', '2019-01-11 14:52:38', '93141', 'SystemUserController', 'execUpdateUserInfo', '业务日志', '93141', '执行：个人设置', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('61', '2019-01-11 14:54:05', '93141', 'SystemRoleController', 'execAddSystemRolePerm', '业务日志', '8', '执行：角色管理-权限配置', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('62', '2019-01-11 14:54:12', '93141', 'LoginController', 'logOut', '登录日志', '93141', '退出系统', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('63', '2019-01-11 14:54:21', '93141', 'LoginController', 'login', '登录日志', '93141', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('64', '2019-01-11 14:54:45', '93141', 'LoginController', 'logOut', '登录日志', '93141', '退出系统', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('65', '2019-01-11 14:54:49', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '2130706433', '2130706433', null, null);
INSERT INTO `system_log` VALUES ('66', '2019-01-13 09:52:07', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('67', '2019-01-13 10:24:26', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('68', '2019-01-13 10:25:54', '1', 'SystemOrgController', 'execUpdateSystemOrg', '业务日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('69', '2019-01-13 10:31:40', '1', 'SystemOrgController', 'execUpdateSystemOrg', '业务日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('70', '2019-01-13 10:32:39', '1', 'SystemOrgController', 'execUpdateSystemOrg', '业务日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('71', '2019-01-13 10:39:50', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('72', '2019-01-13 10:40:06', '1', 'SystemOrgController', 'execUpdateSystemOrg', '登录日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('73', '2019-01-13 10:40:55', '1', 'SystemOrgController', 'execUpdateSystemOrg', '登录日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('74', '2019-01-13 10:46:11', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('75', '2019-01-13 10:46:19', '1', 'SystemUserController', 'resetSystemUserPwd', '登录日志', '84151', '执行：用户管理-重置密码', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('76', '2019-01-13 10:48:25', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('77', '2019-01-13 10:49:29', '1', 'SystemOrgController', 'execUpdateSystemOrg', '登录日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('78', '2019-01-13 10:50:53', '1', 'SystemOrgController', 'execUpdateSystemOrg', '登录日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('79', '2019-01-13 10:51:55', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('80', '2019-01-13 10:57:27', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('81', '2019-01-13 10:57:35', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('82', '2019-01-13 10:59:01', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('83', '2019-01-13 10:59:09', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('84', '2019-01-13 11:00:19', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('85', '2019-01-13 11:02:08', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('86', '2019-01-13 11:02:16', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('87', '2019-01-13 11:04:05', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('88', '2019-01-13 11:04:13', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('89', '2019-01-13 11:06:00', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('90', '2019-01-13 11:06:09', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('91', '2019-01-13 11:25:43', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('92', '2019-01-13 11:25:58', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('93', '2019-01-13 11:26:09', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: java.lang.reflect.InvocationTargetExcept', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('94', '2019-01-13 13:40:47', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('95', '2019-01-13 13:41:25', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: com.faceghost.elasticbg.base.exception.B', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('96', '2019-01-13 13:42:29', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: com.faceghost.elasticbg.base.exception.B', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('97', '2019-01-13 13:43:36', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: com.faceghost.elasticbg.base.exception.B', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('98', '2019-01-13 13:49:17', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('99', '2019-01-13 13:50:19', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: com.faceghost.elasticbg.base.exception.B', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('100', '2019-01-13 13:51:11', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: com.faceghost.elasticbg.base.exception.B', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('101', '2019-01-13 14:03:43', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('102', '2019-01-13 14:03:55', '1', 'SystemOrgController', 'execUpdateSystemOrg', '业务日志', '28', '执行：组织管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('103', '2019-01-13 14:04:45', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：com.anoyi.grpc.exception.GrpcException: com.faceghost.elasticbg.base.exception.BusiException: 操作受限：当前系统正在以【只读】模式运行！\r\n	at com.faceghost.elasticbg.aop.TransactionAop.doBefore(TransactionAop.java:51)\r\n	at sun.reflect.GeneratedMethodAccessor177.invoke(Un', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('104', '2019-01-13 14:15:57', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('105', '2019-01-13 14:52:54', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('106', '2019-01-13 14:53:51', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', '执行：组织管理-更新，异常：java.lang.ArithmeticException: / by zero\r\n	at com.faceghost.elasticbg.controller.SystemOrgController.execUpdateSystemOrg(SystemOrgController.java:305)\r\n	at com.faceghost.elasticbg.controller.SystemOrgController$$FastClassBySpringCGLIB$$584d4a7c.invok', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('107', '2019-01-18 10:09:53', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('108', '2019-01-18 10:42:12', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('109', '2019-01-18 10:42:31', '1', 'SystemOrgController', 'execAddSystemOrg', '业务日志', 'null', '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('110', '2019-01-18 10:43:38', '1', 'SystemOrgController', 'execAddSystemOrg', '业务日志', 'null', '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('111', '2019-01-18 10:44:04', '1', 'SystemOrgController', 'execAddSystemOrg', '业务日志', 'null', '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('112', '2019-01-18 10:44:32', '1', 'SystemOrgController', 'execAddSystemOrg', '异常日志', 'null', '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('113', '2019-01-18 10:45:54', '1', 'SystemOrgController', 'execAddSystemOrg', '异常日志', 'null', '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('114', '2019-01-18 10:46:54', '1', 'SystemOrgController', 'execAddSystemOrg', '异常日志', 'null', '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('115', '2019-01-18 10:47:10', '1', 'SystemOrgController', 'execAddSystemOrg', '异常日志', 'null', '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('116', '2019-01-18 10:48:10', '1', 'SystemOrgController', 'execAddSystemOrg', '异常日志', null, '执行：组织管理-新增', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('117', '2019-01-18 10:50:36', '1', 'SystemOrgController', 'execAddSystemOrg', '异常日志', null, 'java.lang.ArithmeticException: / by zero\r\n	at com.faceghost.elasticbg.controller.SystemOrgController.execAddSystemOrg(SystemOrgController.java:201)\r\n	at com.faceghost.elasticbg.controller.SystemOrgController$$FastClassBySpringCGLIB$$584d4a7c.invoke(<', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('118', '2019-01-18 11:00:23', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('119', '2019-01-18 11:04:35', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('120', '2019-01-18 11:09:46', '1', 'SystemOrgController', 'execAddSystemOrg', '异常日志', null, 'java.lang.ArithmeticException: / by zero\r\n	at com.faceghost.elasticbg.controller.SystemOrgController.execAddSystemOrg(SystemOrgController.java:197)\r\n	at com.faceghost.elasticbg.controller.SystemOrgController$$FastClassBySpringCGLIB$$584d4a7c.invoke(<', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('121', '2019-01-18 13:28:26', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('122', '2019-01-18 13:47:48', '1', 'SystemOrgController', 'execUpdateSystemOrg', '异常日志', '28', 'java.lang.ArithmeticException: / by zero\r\n	at com.faceghost.elasticbg.controller.SystemOrgController.execUpdateSystemOrg(SystemOrgController.java:267)\r\n	at com.faceghost.elasticbg.controller.SystemOrgController$$FastClassBySpringCGLIB$$584d4a7c.invok', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('123', '2019-01-18 14:03:52', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('124', '2019-01-18 14:06:11', '1', 'LoginController', 'login', '登录日志', '1', '登陆成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('125', '2019-01-18 14:58:12', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('126', '2019-01-18 14:58:29', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('127', '2019-01-18 14:58:35', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，失败：用户名或密码错误', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('128', '2019-01-18 14:58:42', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('129', '2019-01-18 14:59:18', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('130', '2019-01-18 14:59:24', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('131', '2019-01-18 15:00:56', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '4294967295', '0', null, null);
INSERT INTO `system_log` VALUES ('132', '2019-01-18 15:01:03', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('133', '2019-01-18 15:01:57', '1', 'SystemUserController', 'execUpdateUserInfo', '业务日志', '1', '执行：个人设置', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('134', '2019-01-18 15:02:10', '1', 'SystemUserController', 'resetSystemUserPwd', '业务日志', '81121', '执行：用户管理-重置密码', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('135', '2019-01-18 15:02:13', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '58131', '执行：用户管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('136', '2019-01-18 15:11:53', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('137', '2019-01-18 15:14:37', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，失败：用户名或密码错误', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('138', '2019-01-18 15:14:42', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('139', '2019-01-18 15:19:49', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('140', '2019-01-18 15:26:14', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，失败：用户名或密码错误', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('141', '2019-01-18 15:26:22', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('142', '2019-01-18 15:31:47', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('143', '2019-01-18 16:18:36', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('144', '2019-01-21 14:56:12', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('145', '2019-01-21 14:57:42', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('146', '2019-01-21 15:07:58', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('147', '2019-01-21 15:15:05', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('148', '2019-01-21 15:17:22', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('149', '2019-01-21 15:25:39', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('150', '2019-01-21 15:48:19', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('151', '2019-01-21 15:54:43', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '58131', '执行：用户管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('152', '2019-01-21 15:54:46', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '93141', '执行：用户管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('153', '2019-01-21 15:54:51', '1', 'SystemUserController', 'resetSystemUserPwd', '业务日志', '84151', '执行：用户管理-重置密码', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('154', '2019-02-11 09:51:46', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('155', '2019-02-11 09:57:38', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('156', '2019-02-11 10:13:59', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('157', '2019-02-11 10:17:45', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('158', '2019-02-11 10:41:28', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('159', '2019-02-11 10:46:12', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('160', '2019-02-11 10:50:44', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('161', '2019-02-11 10:56:01', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('162', '2019-02-11 10:57:57', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('163', '2019-02-11 14:24:28', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('164', '2019-02-11 14:33:15', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('165', '2019-02-11 14:34:36', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('166', '2019-02-11 14:41:31', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('167', '2019-02-11 14:58:06', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('168', '2019-02-11 15:01:43', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('169', '2019-02-11 15:07:52', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('170', '2019-02-11 15:14:03', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，失败：用户名或密码错误', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('171', '2019-02-11 15:14:12', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('172', '2019-02-11 15:21:00', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('173', '2019-02-11 15:21:12', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '84151', '执行：用户管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('174', '2019-02-11 15:26:29', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('175', '2019-02-11 15:29:26', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('176', '2019-02-11 15:30:08', '1', 'SystemUserController', 'execUpdateUserInfo', '业务日志', '1', '执行：个人设置', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('177', '2019-02-11 15:39:43', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('178', '2019-02-11 15:40:01', '1', 'SystemUserController', 'resetSystemUserPwd', '业务日志', '84151', '执行：用户管理-重置密码', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('179', '2019-02-12 09:30:36', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('180', '2019-02-12 09:33:04', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('181', '2019-02-12 09:54:19', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('182', '2019-02-12 10:50:02', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '3232264481', '3232264481', null, null);
INSERT INTO `system_log` VALUES ('183', '2019-02-12 10:55:07', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '3232264481', '3232264481', null, null);
INSERT INTO `system_log` VALUES ('184', '2019-02-12 11:00:55', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '3232264481', '3232264481', null, null);
INSERT INTO `system_log` VALUES ('185', '2019-02-12 15:36:09', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('186', '2019-02-12 16:17:33', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('187', '2019-02-12 16:26:42', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('188', '2019-02-12 16:26:56', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，失败：用户名或密码错误', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('189', '2019-02-12 16:27:04', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('190', '2019-02-12 16:29:54', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('191', '2019-02-12 16:33:38', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，失败：用户名或密码错误', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('192', '2019-02-12 16:33:44', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('193', '2019-02-12 16:34:00', '1', 'SystemUserController', 'resetSystemUserPwd', '业务日志', '98111', '执行：用户管理-重置密码', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('194', '2019-02-12 16:36:41', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '58131', '执行：用户管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('195', '2019-02-12 16:59:55', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('196', '2019-02-12 17:03:43', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('197', '2019-02-12 17:04:14', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '58131', '执行：用户管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('198', '2019-02-12 17:17:12', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('199', '2019-02-12 17:21:54', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('200', '2019-02-12 17:25:03', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('201', '2019-02-13 09:53:39', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('202', '2019-02-13 10:03:43', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('203', '2019-02-13 10:04:01', '1', 'SystemUserController', 'changePwd', '业务日志', '1', '执行：修改密码', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('204', '2019-02-13 10:04:01', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('205', '2019-02-13 10:04:13', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('206', '2019-02-13 11:14:01', '1', 'SystemUserController', 'execUpdateSystemUser', '业务日志', '84151', '执行：用户管理-更新', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('207', '2019-02-13 11:14:21', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('208', '2019-02-13 11:23:44', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('209', '2019-02-13 11:27:10', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('210', '2019-02-13 11:27:57', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('211', '2019-02-13 11:27:59', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('212', '2019-02-13 12:15:10', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('213', '2019-02-13 15:01:25', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('214', '2019-02-16 16:26:23', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('215', '2019-02-27 21:10:06', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('216', '2019-02-27 21:11:53', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('217', '2019-02-27 21:11:55', '1', 'LoginController', 'login', '登录日志', '1', '执行：登陆，成功', '0', '0', null, null);
INSERT INTO `system_log` VALUES ('218', '2019-02-27 21:12:20', '1', 'LoginController', 'logOut', '登录日志', '1', '执行：退出登录', '0', '0', null, '');

-- ----------------------------
-- Table structure for system_org
-- ----------------------------
DROP TABLE IF EXISTS `system_org`;
CREATE TABLE `system_org` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(150) NOT NULL COMMENT '组织名称',
  `ps` varchar(300) DEFAULT NULL COMMENT '备注',
  `p_id` int(11) NOT NULL COMMENT '上层组织ID',
  `insert_t` datetime DEFAULT NULL COMMENT '登记时间',
  `update_t` datetime DEFAULT NULL COMMENT '更新时间',
  `insert_u` varchar(50) DEFAULT NULL COMMENT '登记人员',
  `update_u` varchar(50) DEFAULT '' COMMENT '更新人员',
  `link_man` varchar(30) DEFAULT NULL COMMENT '联系人',
  `pin_yin` varchar(30) DEFAULT NULL COMMENT '组织名称拼音',
  `address` varchar(300) DEFAULT NULL COMMENT '地址',
  `fixed_phone` varchar(30) DEFAULT NULL COMMENT '固话',
  `fax` varchar(30) DEFAULT NULL COMMENT '传真',
  `is_auto_expand` char(1) DEFAULT NULL COMMENT '树形菜单是否展开，1：是，0：否',
  `is_leaf` char(1) DEFAULT NULL COMMENT '树形菜单是否为叶子节点，1：是，0：否',
  `icon` varchar(250) DEFAULT NULL COMMENT '树形菜单图标',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1：正常，0：冻结',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_org
-- ----------------------------
INSERT INTO `system_org` VALUES ('1', '总部', ' ', '0', '2018-01-03 00:00:00', null, '1', null, null, null, null, null, '1', '0', '', '', '1');
INSERT INTO `system_org` VALUES ('2', '总裁办', ' ', '1', '2018-01-02 12:00:05', '2019-01-04 11:24:39', '1', '1', '李四', 'ZCB', '上海市宝山区同济路23弄', '021-2526453', '021-2526453', '0', '', '', '1');
INSERT INTO `system_org` VALUES ('3', '秘书部', ' ', '2', '2018-01-03 14:01:00', '2019-01-04 15:00:09', '1', '1', '李四', '', ' ', ' ', ' ', '1', '', '', '1');
INSERT INTO `system_org` VALUES ('4', '上海分公司', ' ', '1', '2018-11-16 10:55:39', '2018-11-16 10:55:39', '1', '1', '李四', 'SH', '', '123', '', '0', '0', ' ', '1');
INSERT INTO `system_org` VALUES ('5', '技术部', ' ', '4', '2018-11-15 16:07:26', '2019-01-04 11:22:07', '1', '1', '张三', '', '上海徐家汇231号', '12', '1', '0', '0', ' ', '1');
INSERT INTO `system_org` VALUES ('6', '北京分公司', ' ', '1', '2018-11-22 14:54:18', '2019-01-04 11:02:28', '1', '1', '李四', 'BJ', '', '', '', '0', '0', ' ', '1');
INSERT INTO `system_org` VALUES ('7', '河南分公司', '', '1', '2019-01-04 10:37:37', '2019-01-04 12:29:52', '1', '1', '李四', 'HN', '', '', '', '0', '0', ' ', '1');
INSERT INTO `system_org` VALUES ('8', '浙江分公司', ' ', '1', '2018-12-03 17:14:38', '2019-01-04 14:59:47', '1', '1', '李四', 'ZJ', '', '', '', '1', '0', ' ', '1');
INSERT INTO `system_org` VALUES ('9', '技术部', ' ', '7', '2018-12-04 10:05:00', '2019-01-11 09:25:36', '1', '1', '李四', '', '', '', '', '1', '1', ' ', '0');
INSERT INTO `system_org` VALUES ('10', '天津分公司', null, '1', '2019-01-04 11:25:20', null, '1', null, '李四', 'TJ', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('11', '重庆分公司', null, '1', '2019-01-04 11:25:42', null, '1', null, '李四', 'CQ', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('12', '河北分公司', null, '1', '2019-01-04 11:25:58', null, '1', null, '李四', 'HB', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('13', '山西飞公司', null, '1', '2019-01-04 11:26:20', null, '1', null, '李四', 'SX', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('14', '辽宁分公司', null, '1', '2019-01-04 11:26:40', null, '1', null, '李四', 'LN', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('15', '黑龙江分公司', null, '1', '2019-01-04 11:26:57', null, '1', null, '李四', 'HLJ', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('16', '江苏分公司', null, '1', '2019-01-04 11:27:30', null, '1', null, '李四', 'JS', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('18', '安徽分公司', null, '1', '2019-01-04 11:28:06', null, '1', null, '李四', 'AH', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('19', '福建分公司', null, '1', '2019-01-04 11:28:24', null, '1', null, '李四', 'FJ', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('20', '江西分公司', null, '1', '2019-01-04 11:28:48', null, '1', null, '李四', 'JX', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('21', '山东分公司', null, '1', '2019-01-04 11:29:03', null, '1', null, '李四', 'SD', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('22', '湖北分公司', null, '1', '2019-01-04 11:29:25', null, '1', null, '李四', 'HB', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('23', '湖南分公司', null, '1', '2019-01-04 11:29:49', null, '1', null, '李四', 'HN', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('24', '广东分公司', null, '1', '2019-01-04 11:30:12', null, '1', null, '李四', 'GD', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('25', '海南分公司', null, '1', '2019-01-04 11:30:31', null, '1', null, '李四', 'HN', '', '', '', '0', null, null, '1');
INSERT INTO `system_org` VALUES ('26', '四川分公司', null, '1', '2019-01-04 11:30:46', '2019-01-04 14:57:50', '1', '1', '李四', 'SC', '', '', '', '1', null, null, '1');
INSERT INTO `system_org` VALUES ('27', '陕西分公司', null, '1', '2019-01-04 14:58:15', '2019-01-09 10:15:23', '1', '1', '李四', 'SX', '', '', '', '1', null, null, '1');
INSERT INTO `system_org` VALUES ('28', '客服部', null, '1', '2019-01-10 19:18:15', '2019-01-13 14:03:55', '1', '1', '李四', '', '', '', '', '1', null, null, '1');

-- ----------------------------
-- Table structure for system_params
-- ----------------------------
DROP TABLE IF EXISTS `system_params`;
CREATE TABLE `system_params` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `insert_t` datetime DEFAULT NULL COMMENT '登记时间',
  `insert_u` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '登记人员',
  `update_t` datetime DEFAULT NULL COMMENT '更新时间',
  `update_u` varchar(30) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新人员',
  `type` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '类型，sequence：序列',
  `param` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数',
  `value` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '参数值',
  `status` tinyint(4) NOT NULL COMMENT '状态，1：正常，0：冻结',
  `ps` varchar(255) CHARACTER SET utf8 DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of system_params
-- ----------------------------
INSERT INTO `system_params` VALUES ('1', '2018-11-23 09:53:46', '1', '2019-01-08 17:43:52', '1', 'sequence', 'system_user_id', '15', '1', '系统管理员表（system_params）序列');
INSERT INTO `system_params` VALUES ('2', '2019-01-06 13:39:26', '1', '2019-01-06 13:41:05', '1', '4', '3', '2', '0', '1');

-- ----------------------------
-- Table structure for system_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_permission`;
CREATE TABLE `system_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) NOT NULL COMMENT '权限名称',
  `type` varchar(10) NOT NULL COMMENT '权限级别，1：一级，2：二级，3：三级，4：四级，5：五级',
  `url` varchar(50) DEFAULT '' COMMENT '访问URL',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1：正常，0：冻结',
  `p_id` int(11) NOT NULL COMMENT '父权限ID',
  `permission` varchar(150) DEFAULT NULL COMMENT '权限',
  `insert_t` datetime NOT NULL COMMENT '登记时间',
  `update_t` datetime DEFAULT NULL COMMENT '更新时间',
  `insert_u` varchar(50) NOT NULL COMMENT '登记人员',
  `update_u` varchar(50) DEFAULT NULL COMMENT '更新人员',
  `ps` varchar(150) DEFAULT NULL COMMENT '备注',
  `position` int(11) DEFAULT NULL COMMENT '排序',
  `is_auto_expand` char(1) DEFAULT NULL COMMENT '树形菜单是否展开，1：是，0：否',
  `is_leaf` char(1) DEFAULT NULL COMMENT '树形菜单是否为叶子节点，1：是，0：否',
  `icon` varchar(200) DEFAULT NULL COMMENT '树形菜单图标',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_permission
-- ----------------------------
INSERT INTO `system_permission` VALUES ('1', '权限', '0', null, '1', '0', '', '2016-03-31 19:23:42', null, '1', null, null, '1', '0', '', '');
INSERT INTO `system_permission` VALUES ('2', '系统管理', '1', '', '1', '1', '1', '2017-12-03 14:49:45', '2019-01-06 15:18:44', '1', '1', '1', '1', '1', '', 'config1.png');
INSERT INTO `system_permission` VALUES ('3', '系统资源', '2', '', '1', '2', '', '2017-12-31 17:31:24', '2019-01-07 13:47:12', '1', '1', '', null, '0', '', 'folder1.png');
INSERT INTO `system_permission` VALUES ('4', '权限管理', '2', '', '1', '2', '', '2017-12-31 17:31:47', '2019-01-07 13:47:10', '1', '1', '', null, '0', '', 'folder1.png');
INSERT INTO `system_permission` VALUES ('5', '账号管理', '2', '', '1', '2', '', '2017-12-31 17:32:25', '2019-01-07 13:47:07', '1', '1', '', null, '0', '', 'folder1.png');
INSERT INTO `system_permission` VALUES ('6', '系统图标', '3', 'system/system_icon', '1', '3', 'system:icon:view', '2017-12-31 17:33:37', '2019-01-08 10:45:48', '1', '1', '', null, '0', '', 'layout.png');
INSERT INTO `system_permission` VALUES ('7', '系统参数', '3', 'system/system_params', '1', '3', 'system:params:view', '2017-12-31 17:33:54', '2019-01-06 15:21:00', '1', '1', '', null, '0', '', 'key.png');
INSERT INTO `system_permission` VALUES ('8', '权限配置', '3', 'system/system_perm', '1', '4', 'system:perm:view', '2017-12-31 17:34:21', '2019-01-09 10:54:22', '1', '1', '', null, '0', '', 'lock3.png');
INSERT INTO `system_permission` VALUES ('9', '角色管理', '3', 'system/system_role', '1', '4', 'system:role:view', '2018-11-20 16:44:48', '2019-01-09 10:40:54', '1', '1', '', null, '0', null, 'user6.png');
INSERT INTO `system_permission` VALUES ('10', '组织管理', '2', '', '1', '2', '', '2018-11-25 14:24:54', '2019-01-07 13:47:04', '1', '1', '', null, '0', null, 'folder1.png');
INSERT INTO `system_permission` VALUES ('11', '组织管理', '3', 'system/system_org', '1', '10', 'system:org:view', '2018-11-21 16:34:18', '2019-01-09 10:07:35', '1', '1', '', null, '0', null, 'user6.png');
INSERT INTO `system_permission` VALUES ('16', '用户管理', '3', 'system/system_user', '1', '5', 'system:user:view', '2018-11-22 13:48:10', '2019-01-10 17:31:02', '1', '1', '', null, '0', null, 'user6.png');
INSERT INTO `system_permission` VALUES ('17', '新增', '4', '', '1', '7', 'system:params:add', '2019-01-08 13:58:38', '2019-01-08 14:02:33', '1', '1', '', null, '0', null, 'icon152.png');
INSERT INTO `system_permission` VALUES ('18', '更新', '4', '', '1', '7', 'system:params:update', '2019-01-08 13:58:56', '2019-01-08 17:05:41', '1', '1', '', null, '0', null, 'icon152.png');
INSERT INTO `system_permission` VALUES ('19', '新增', '4', '', '1', '11', 'system:org:add', '2019-01-09 10:07:56', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('20', '更新', '4', '', '1', '11', 'system:org:update', '2019-01-09 10:08:13', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('21', '新增', '4', '', '1', '16', 'system:user:add', '2019-01-09 10:26:24', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('22', '更新', '4', '', '1', '16', 'system:user:update', '2019-01-09 10:26:40', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('23', '重置密码', '4', '', '1', '16', 'system:user:resetPwd', '2019-01-09 10:27:02', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('24', '新增', '4', '', '1', '9', 'system:role:add', '2019-01-09 10:41:25', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('25', '更新', '4', '', '1', '9', 'system:role:update', '2019-01-09 10:41:39', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('26', '权限配置', '4', '', '1', '9', 'system:role:settingPerm', '2019-01-09 10:42:05', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('27', '新增', '4', '', '1', '8', 'system:perm:add', '2019-01-09 10:55:15', null, '1', null, '', null, '0', null, '');
INSERT INTO `system_permission` VALUES ('28', '更新', '4', '', '1', '8', 'system:perm:update', '2019-01-09 10:55:30', '2019-01-09 10:55:45', '1', '1', '', null, '0', null, 'icon152.png');
INSERT INTO `system_permission` VALUES ('29', '操作日志', '3', 'system/system_log', '1', '3', 'system:log:view', '2019-01-10 16:07:54', '2019-01-10 17:43:18', '1', '1', '', null, '0', null, 'icon15.png');

-- ----------------------------
-- Table structure for system_role
-- ----------------------------
DROP TABLE IF EXISTS `system_role`;
CREATE TABLE `system_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态，1：正常，0：冻结',
  `insert_t` datetime NOT NULL COMMENT '登记时间',
  `update_t` datetime DEFAULT NULL COMMENT '更新时间',
  `insert_u` varchar(50) NOT NULL COMMENT '登记人员',
  `update_u` varchar(50) DEFAULT NULL COMMENT '更新人员',
  `system_org_id` int(11) DEFAULT NULL COMMENT '所属组织',
  `ps` varchar(150) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role
-- ----------------------------
INSERT INTO `system_role` VALUES ('1', '角色管理', null, '2016-10-14 20:37:22', null, '1', null, '2', '1');
INSERT INTO `system_role` VALUES ('2', '管理员', '1', '2018-11-21 15:17:05', '2018-11-21 15:17:05', '1', '1', '2', '1');
INSERT INTO `system_role` VALUES ('3', '技术部2', '1', '2018-11-22 11:12:46', '2018-11-22 11:12:46', '1', '1', '1', '');
INSERT INTO `system_role` VALUES ('4', '财务部', '1', '2018-11-22 16:47:13', null, '1', null, '1', '');
INSERT INTO `system_role` VALUES ('5', '市场部3', '1', '2018-11-22 16:48:20', '2018-12-04 14:11:10', '1', '1', '1', '');
INSERT INTO `system_role` VALUES ('6', '运营部', '1', '2018-11-22 16:48:23', '2018-11-22 16:48:23', '1', '1', '1', '');
INSERT INTO `system_role` VALUES ('7', '技术部', '1', '2018-12-23 14:17:40', '2019-01-04 17:19:27', '1', '1', '7', '');
INSERT INTO `system_role` VALUES ('8', '测试', '1', '2019-01-08 10:44:02', null, '1', null, '1', '');
INSERT INTO `system_role` VALUES ('9', '超级管理员', '1', '2019-01-09 11:02:57', null, '1', null, '1', '');
INSERT INTO `system_role` VALUES ('10', '临时', '1', '2019-01-10 17:17:22', '2019-01-10 19:16:12', '1', '1', '1', '');

-- ----------------------------
-- Table structure for system_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `system_role_permission`;
CREATE TABLE `system_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_role_id` int(11) NOT NULL COMMENT '所属角色',
  `system_permission_id` int(11) NOT NULL COMMENT '所属权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_role_permission
-- ----------------------------
INSERT INTO `system_role_permission` VALUES ('118', '6', '15');
INSERT INTO `system_role_permission` VALUES ('119', '6', '14');
INSERT INTO `system_role_permission` VALUES ('120', '6', '2');
INSERT INTO `system_role_permission` VALUES ('121', '6', '10');
INSERT INTO `system_role_permission` VALUES ('122', '6', '11');
INSERT INTO `system_role_permission` VALUES ('123', '6', '5');
INSERT INTO `system_role_permission` VALUES ('124', '6', '16');
INSERT INTO `system_role_permission` VALUES ('125', '6', '4');
INSERT INTO `system_role_permission` VALUES ('126', '6', '9');
INSERT INTO `system_role_permission` VALUES ('127', '6', '8');
INSERT INTO `system_role_permission` VALUES ('128', '6', '3');
INSERT INTO `system_role_permission` VALUES ('129', '6', '7');
INSERT INTO `system_role_permission` VALUES ('130', '6', '6');
INSERT INTO `system_role_permission` VALUES ('272', '9', '2');
INSERT INTO `system_role_permission` VALUES ('273', '9', '10');
INSERT INTO `system_role_permission` VALUES ('274', '9', '11');
INSERT INTO `system_role_permission` VALUES ('275', '9', '20');
INSERT INTO `system_role_permission` VALUES ('276', '9', '19');
INSERT INTO `system_role_permission` VALUES ('277', '9', '5');
INSERT INTO `system_role_permission` VALUES ('278', '9', '16');
INSERT INTO `system_role_permission` VALUES ('279', '9', '23');
INSERT INTO `system_role_permission` VALUES ('280', '9', '22');
INSERT INTO `system_role_permission` VALUES ('281', '9', '21');
INSERT INTO `system_role_permission` VALUES ('282', '9', '4');
INSERT INTO `system_role_permission` VALUES ('283', '9', '9');
INSERT INTO `system_role_permission` VALUES ('284', '9', '26');
INSERT INTO `system_role_permission` VALUES ('285', '9', '25');
INSERT INTO `system_role_permission` VALUES ('286', '9', '24');
INSERT INTO `system_role_permission` VALUES ('287', '9', '8');
INSERT INTO `system_role_permission` VALUES ('288', '9', '28');
INSERT INTO `system_role_permission` VALUES ('289', '9', '27');
INSERT INTO `system_role_permission` VALUES ('290', '9', '3');
INSERT INTO `system_role_permission` VALUES ('291', '9', '29');
INSERT INTO `system_role_permission` VALUES ('292', '9', '7');
INSERT INTO `system_role_permission` VALUES ('293', '9', '18');
INSERT INTO `system_role_permission` VALUES ('294', '9', '17');
INSERT INTO `system_role_permission` VALUES ('295', '9', '6');
INSERT INTO `system_role_permission` VALUES ('301', '10', '10');
INSERT INTO `system_role_permission` VALUES ('302', '8', '2');
INSERT INTO `system_role_permission` VALUES ('303', '8', '10');
INSERT INTO `system_role_permission` VALUES ('304', '8', '11');
INSERT INTO `system_role_permission` VALUES ('305', '8', '20');
INSERT INTO `system_role_permission` VALUES ('306', '8', '19');
INSERT INTO `system_role_permission` VALUES ('307', '8', '5');
INSERT INTO `system_role_permission` VALUES ('308', '8', '16');
INSERT INTO `system_role_permission` VALUES ('309', '8', '23');
INSERT INTO `system_role_permission` VALUES ('310', '8', '22');
INSERT INTO `system_role_permission` VALUES ('311', '8', '21');
INSERT INTO `system_role_permission` VALUES ('312', '8', '4');
INSERT INTO `system_role_permission` VALUES ('313', '8', '9');
INSERT INTO `system_role_permission` VALUES ('314', '8', '26');
INSERT INTO `system_role_permission` VALUES ('315', '8', '25');
INSERT INTO `system_role_permission` VALUES ('316', '8', '24');
INSERT INTO `system_role_permission` VALUES ('317', '8', '8');
INSERT INTO `system_role_permission` VALUES ('318', '8', '28');
INSERT INTO `system_role_permission` VALUES ('319', '8', '27');
INSERT INTO `system_role_permission` VALUES ('320', '8', '3');
INSERT INTO `system_role_permission` VALUES ('321', '8', '29');
INSERT INTO `system_role_permission` VALUES ('322', '8', '7');
INSERT INTO `system_role_permission` VALUES ('323', '8', '18');
INSERT INTO `system_role_permission` VALUES ('324', '8', '17');
INSERT INTO `system_role_permission` VALUES ('325', '8', '6');

-- ----------------------------
-- Table structure for system_user
-- ----------------------------
DROP TABLE IF EXISTS `system_user`;
CREATE TABLE `system_user` (
  `id` varchar(50) NOT NULL COMMENT '主键',
  `name` varchar(60) NOT NULL COMMENT '用户账号',
  `password` varchar(60) NOT NULL COMMENT '用户密码，暗文，不可逆',
  `init_password` char(6) DEFAULT NULL COMMENT '初始、重置密码，明文',
  `salt` varchar(60) NOT NULL COMMENT '加密盐',
  `system_org_id` int(11) DEFAULT NULL COMMENT '所属组织',
  `status` tinyint(4) NOT NULL COMMENT '状态，1：正常，0：冻结',
  `insert_t` datetime NOT NULL COMMENT '登记时间',
  `update_t` datetime DEFAULT NULL COMMENT '更新时间',
  `insert_u` varchar(50) DEFAULT NULL COMMENT '登记人员',
  `update_u` varchar(50) DEFAULT NULL COMMENT '更新人员',
  `tel_phone` varchar(30) DEFAULT NULL COMMENT '手机',
  `fixed_phone` varchar(30) DEFAULT NULL COMMENT '固话',
  `real_name` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `email` varchar(60) DEFAULT NULL COMMENT '电子邮箱',
  `qq` int(11) DEFAULT NULL COMMENT 'QQ',
  `address` varchar(300) DEFAULT NULL COMMENT '地址',
  `ps` varchar(300) DEFAULT NULL COMMENT '备注',
  `last_retry_time` datetime DEFAULT NULL COMMENT '最后一次尝试登记系统时间',
  `retry_number` int(11) DEFAULT NULL COMMENT '登录系统失败次数',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_unique` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user
-- ----------------------------
INSERT INTO `system_user` VALUES ('1', 'super_admin', '06d07da2cde5ec462ffa92e032ab3316', 'admin', 'fb8936c73dc495cd7f40c5bbd183b26d', '1', '1', '2018-11-23 15:18:42', '2019-02-11 15:30:08', '1', '1', '15188888888', '021-888888', '系统超级管理员', 'service@elastic-bg.com', '88888888', '上海市徐家汇222弄', '', '2019-02-27 21:11:55', '0');
INSERT INTO `system_user` VALUES ('2321', 'test1', '1c242cd62c49cca903a8bd90075cf6c2', 'admin', '4f03110b57edfd36a67b4ef13d803ba8', '1', '0', '2018-11-23 15:39:30', '2018-11-28 15:57:04', '1', '1', '1', '2', '测试', '3', '4', '5', '', null, '0');
INSERT INTO `system_user` VALUES ('58131', 'zb_seo', '2617ff2413b9b08239a47d8511d17352', '93Cnva', '6a0e343c3e8d7b58d51ad637dca3d61a', '1', '1', '2019-01-07 15:35:43', '2019-02-12 17:04:14', '1', '1', '', '', '总部-SEO', '', null, '', '', null, null);
INSERT INTO `system_user` VALUES ('81121', 'sh_tech', '045d130ba5732a323deb18fdedc6ba48', 'PolUAg', 'f7f58f4656ed9b8c19a3dad7eeadfa3f', '5', '1', '2018-12-23 15:00:42', '2019-01-10 19:16:32', '1', '1', '', '', '上海技术部', '', null, '', '', null, null);
INSERT INTO `system_user` VALUES ('84151', 'zb_tmp', '9fe84fc75f37cc6dd92aacfa5b2506bb', 'BRhtpy', '7852e4c07aec139a1aa32ad492cf3acc', '1', '1', '2019-01-10 16:41:25', '2019-02-13 11:14:01', '1', '1', '', '', '总部临时', '', null, '', '', null, null);
INSERT INTO `system_user` VALUES ('93141', 'zb_test', 'ac8f8b227f899ddf824c7ee4aeaebbcf', 'OEhss4', '1c86dabc356b8331feb40cec724b1bac', '1', '1', '2019-01-08 10:44:39', '2019-01-21 15:54:46', '1', '1', '', '', '总部-测试', '', null, '', '', '2019-01-08 14:15:33', '0');
INSERT INTO `system_user` VALUES ('98111', 'it_support', 'aeb1455118646605bdedaa25b37d746f', 'Fx4Xmn', '932546c477bddb07448846a6138d1547', '1', '1', '2018-11-25 14:36:03', '2018-12-04 14:49:22', '1', '1', '', '1', '技术支持', '', null, '', '', null, null);

-- ----------------------------
-- Table structure for system_user_role
-- ----------------------------
DROP TABLE IF EXISTS `system_user_role`;
CREATE TABLE `system_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `system_user_id` varchar(50) NOT NULL COMMENT '所属用户',
  `system_role_id` int(11) NOT NULL COMMENT '所属角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_user_role
-- ----------------------------
INSERT INTO `system_user_role` VALUES ('41', '2321', '6');
INSERT INTO `system_user_role` VALUES ('42', '2321', '5');
INSERT INTO `system_user_role` VALUES ('43', '2321', '3');
INSERT INTO `system_user_role` VALUES ('52', '98111', '6');
INSERT INTO `system_user_role` VALUES ('53', '98111', '5');
INSERT INTO `system_user_role` VALUES ('56', '1', '9');
INSERT INTO `system_user_role` VALUES ('61', '93141', '8');
INSERT INTO `system_user_role` VALUES ('64', '58131', '6');
INSERT INTO `system_user_role` VALUES ('65', '84151', '8');
