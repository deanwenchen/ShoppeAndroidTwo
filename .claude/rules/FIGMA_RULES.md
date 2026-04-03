# FIGMA_RULES.md
# ============================================
# Figma MCP Workflow Rules
# ============================================

1. MCP 调用顺序：
   1. get_design_context
   2. get_metadata (if needed)
   3. get_screenshot
   4. download assets
   5. generate code
   6. validate against screenshot

2. UI Implementation：
   - 遵循 Figma 节点结构
   - 保持布局层级
   - 禁止硬编码
   - 必须使用 design tokens 与现有组件

3. 资源：
   - MCP 返回 asset URL 直接使用
   - 优先 SVG
   - 禁止使用 placeholder 或外部 icon 库