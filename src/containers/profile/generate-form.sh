#!/bin/bash
# notice: use bash plz

# get timestramp
hms=$(date "+%Y%m%d%H%M%S")

file_name="case-"${hms}".html"
touch ${file_name}

# 在当前目录下创建文本文件temp,如果文件存在则清空文件
$(> temp)

statement='<html><head><title>document</title></head><body>';
statement+='<div class="pack_wrapper">'
statement+='<div class="form_pack">'
statement+='<form class="mine_form">'

# input div collection begin
statement+='<div class="input_wrap_div">'
# input tag varible
index_input='<div class="input_item_div"><input class="input_tag" onchange="" name="" type="text" value="" placeholder="" /></div>';
for (( i = 0; i < 10; i++ )); do
	#statements
	echo ${index_input} >> temp
done

# get the content in the temp text
html2=$(cat temp)
statement+=${html2}
# input div collection end
statement+='</div>'

# button div collection
statement+="<div class="btn_div_items">"

statement+="<div class="btn_input" id="reset_item"><input class="input_btn_ele" type="reset" value="Reset" id="reset_id" /></div>"
statement+="<div class="btn_input" id="submit_item"><input class="input_btn_ele" type="submit" value="Submit" id="submit_id" /></div>"

statement+="</div>"

# close area
statement+="</form></div>"
statement+="<br /><br /><br /><br /><br /><br />"
statement+="</div></body></html>"

# style css
css_var='<style>'
 
css_var+='.input_tag {width: 28em;height: 2.5em;border-radius: 6px;border-width: 0;}'

css_var+='.input_item_div {margin: 20px 0;}'

css_var+='.mine_form {position: relative;left: 12%;top: 4%;}'

css_var+='.form_pack {background: #0f6b4c;width: 30em;height: 98%;position: relative;left: 30%;top: 4em;border-radius: 10px;}'

css_var+='.btn_input {float: left;left: 12%;position: relative;margin: 0 20px;}'

css_var+='.input_btn_ele {border-width: 0;border-radius: 7px;height: 3em;width: 5em;font-size: 18px;}'

css_var+='.btn_div_items {position: relative;top: 1em;}'

css_var+='</style>'

echo ${statement}${css_var} >> ${file_name}