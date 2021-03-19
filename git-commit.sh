#!/bin/sh

# git config user.name "AllStarGH";

# 初次提交
# git init
# git add .
# git commit -m "first commit"
# git branch -M main
# git remote add origin https://github.com/AllStarGH/ComputerSubscribe.git
# git push -u origin main

git status

# 查看当前工作在哪个分支上
git branch -v

# 列出本地和远端分支
git branch -av

# 列出所有 remote
git remote -v

git add .

git commit -m 'second committing to here'

git push