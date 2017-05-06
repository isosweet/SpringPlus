-- 用户
CREATE TABLE user
(
	id varchar(32) NOT NULL COMMENT 'id 主键',
	user_name varchar(30) DEFAULT ''  COMMENT '用户名',
	mobile_number varchar(11)  COMMENT '手机号码',
	email varchar(30)  COMMENT '邮箱',
	password varchar(50)  COMMENT '密码',
	user_type tinyint  COMMENT '用户类型 : 0 普通用户 1 管理用户',
	register_time date COMMENT '注册时间',
	validity char(1)  COMMENT '是否有效 0 有效 1 无效',
	create_date datetime  COMMENT '创建时间 : 创建时间',
	update_date datetime  COMMENT '更新时间 : 更新时间',
	remarks varchar(255) DEFAULT '' COMMENT '备注信息 : 备注信息',
	del_flag char(1) DEFAULT '0'  COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '用户';