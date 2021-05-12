#!/bin/bash
# notice: use bash plz

# 在当前目录下创建文件,如果文件存在则清空文件
$(> index.jsx)
$(> index.less)

class_name=MineClass

context="import React, { Component } from 'react';\n"
context+="import './index.less';"

context+="class ${class_name} extends Component {"

context+="constructor(props) {"
context+="super(props); console.info('${class_name}--Constructor');}" 

context+="componentDidMount() {console.log('${class_name} component did mount');"
context+="console.log(this); }"

context+="state = {}\n"

context+="render() {\n return (<div></div>) \n}\n"

context+="}\n"

context+="export default ${class_name};"

# -e：激活转义字符
echo -e ${context} >> index.jsx