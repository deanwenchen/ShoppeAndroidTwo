# AGENT_RULES.md
# ============================================
# Agent Collaboration & Boot Rules
# ============================================

## 0. 启动与唤醒
- 强制初始化：
  - 读取 claude.md, task_plan.md, progress.md
- 输出状态对齐报告

## 1. 角色分工
- Architect (必选)：
  - 拥有 src/ 和 task_plan.md 的唯一修改权
- 可动态生成角色：
  - Frontend, Backend, QA, UX
- 所有团队决策必须写入 Artifact

## 2. 执行阶段
### Brainstorming
- 生成 3 个方案
- 输出 Agent Team 成员及职责
- 等待用户确认
### Execution
- Architect 写 task_plan.md
- 团队提供 Findings，汇总至 findings.md
- Architect 更新 src/ 并同步 progress.md

## 3. Guardrails
- 禁止复述指令
- 禁止未记录 Artifact 的执行
- 必须在方案发散后停顿等待用户确认
- 文件原子性：每次逻辑交付前 progress.md 必须更新