-- 用户角色相关

-- 用户表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '名字',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `mobile` varchar(30) NULL COMMENT '手机号',
  `email` varchar(100) NULL COMMENT '邮箱',
  `dept` varchar(50) NULL COMMENT '部门',
  `position` varchar(50) NULL COMMENT '职位',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
-- 角色表
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '角色名称',
  `code` varchar(50) NOT NULL COMMENT '角色code',
  `desc` varchar(100) NULL COMMENT '描述',
  `create_time` datetime NOT NULL,
  `modify_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';
-- 菜单权限表
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL,
  `pid` bigint(20) NOT NULL COMMENT '父id',
  `name` varchar(50) NOT NULL COMMENT '菜单名称',
  `code` varchar(50) NOT NULL COMMENT '菜单code',
  `type` int(2) NOT NULL COMMENT '菜单类型',
  `desc` varchar(100) NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单权限表';
-- 用户角色表
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';

-- 角色菜单表
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色菜单表';

-- 项目相关
-- 项目表
CREATE TABLE `project` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '项目名称',
  `type` int(2) NOT NULL COMMENT '项目类型',
  `time_limit` int(6) NOT NULL COMMENT '工期/天',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `total_amount` decimal(10,2) NOT NULL COMMENT '项目总金额',
  `address` varchar(100) NOT NULL COMMENT '项目地点',
  `party_a` varchar(50) NOT NULL COMMENT '甲方',
  `party_b` varchar(50) NOT NULL COMMENT '乙方',
  `project_contract_id` bigint(20) NOT NULL COMMENT '合同id',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目表';
-- 项目合同
CREATE TABLE `project_contract` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '合同名称',
  `type` int(2) NOT NULL COMMENT '合同类型 1 总包合同 2 分包合同',
  `code` varchar(50) NOT NULL COMMENT '合同编号',
  `time_limit` int(6) NOT NULL COMMENT '工期/天',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '结束时间',
  `total_amount` decimal(10,2) NOT NULL COMMENT '项目总金额',
  `address` varchar(100) NOT NULL COMMENT '项目地点',
  `party_a` varchar(50) NOT NULL COMMENT '甲方',
  `party_b` varchar(50) NOT NULL COMMENT '乙方',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目合同';

-- 项目合同资源表
CREATE TABLE `project_contract_resource` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `resource_attachment_id` bigint(20) NOT NULL,
  `project_contract_id` bigint(20) NOT NULL,
  --`project_id` bigint(20) NOT NULL,
  `sort` int(2) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目合同资源表';

-- 项目分包阶段表
CREATE TABLE `project_subpackage` (
  `id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `project_contract_id` bigint(20) NOT NULL COMMENT '合同id',
  --`name` varchar(50) NOT NULL COMMENT '名称',
  `org_name` varchar(50) NOT NULL COMMENT '分包企业名称',
  `type` int(2) NOT NULL COMMENT '分包类型 1 土建（基础） 2 土建（主体） 3 装修 4 基电',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目分包阶段表';

-- 项目节点表
CREATE TABLE `project_phase` (
 `id` bigint(20) NOT NULL,
 `name` varchar(50) NOT NULL COMMENT '节点名称',
 `project_id` bigint(20) NOT NULL COMMENT '项目id',
 `complete` int(3) NOT NULL COMMENT '完成情况 0-100',
 `time_limit` int(6) NOT NULL COMMENT '工期/天',
 `start_date` datetime NOT NULL COMMENT '开始时间',
 `desc` varchar(500) NULL COMMENT '项目节点描述',
 `remark` text NOT NULL COMMENT '备注',
 `end_date` datetime NOT NULL COMMENT '结束时间',
 `create_user` bigint(20) NOT NULL COMMENT '创建人',
 `create_time` datetime NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目节点表';

-- 项目节点款项
CREATE TABLE `project_phase_fund` (
  `id` bigint(20) NOT NULL,
  `project_phase_id` bigint(20) NOT NULL COMMENT '项目节点id',
  `project_id` bigint(20) NOT NULL COMMENT '项目id',
  `name` varchar(50) NOT NULL COMMENT '款项名称',
  `amount`  decimal(10,2) NOT NULL COMMENT '金额',
  `actual_amount` decimal(10,2) NOT NULL COMMENT '实际金额',
  `complete` int(3) NOT NULL COMMENT '完成情况 0-100',
  `status` int(2) NOT NULL COMMENT '状态 0 财务未确认 0 财务已确认',
  `remark` text NOT NULL COMMENT '备注',
  `validator` bigint(20) NOT NULL COMMENT '确认人',
  `create_user` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目节点款项';

-- 项目节点附件表
CREATE TABLE `project_phase_attachment` (
 `id` bigint(20) NOT NULL,
 `resource_attachment_id` bigint(20) NOT NULL,
 `project_phase_id` bigint(20) NOT NULL COMMENT '项目节点id',
 `project_id` bigint(20) NOT NULL COMMENT '项目id',
 `desc` varchar(100) NULL COMMENT '描述',
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='项目节点附件表';

-- 资源附件表
CREATE TABLE `resource_attachment` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL COMMENT '资源名称',
  `url` varchar(500) NOT NULL COMMENT '资源url',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `file_path` varchar(500) NOT NULL COMMENT 'local资源路径',
  `suffix` varchar(20) NOT NULL COMMENT '文件后缀',
  `type` int(2) NOT NULL COMMENT '资源类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='资源附件表';