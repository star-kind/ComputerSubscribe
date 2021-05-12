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
index_input='<div class="input_item_div">';

index_input+='<div class="input_element">'
index_input+='<label for="" class="labels_for_tag">:</label>'
index_input+='</div>';

index_input+='<div class="input_element inputs_side">';
index_input+='<input id="" class="input_tag" onchange="" name="" type="text" value="" placeholder="" />';

index_input+='</div></div>';

for (( i = 0; i < 5; i++ )); do
	#statements
	echo ${index_input} >> temp
done

# get the content in the temp text
html2=$(cat temp)
statement+=${html2}
# input div collection end
statement+='</div>'

# button div collection
statement+='<div class="btn_div_items">'

statement+='<div class="btn_input" id="reset_item"><input class="input_btn_ele" type="reset" value="Reset" id="reset_id" /></div>'
statement+='<div class="btn_input" id="submit_item"><input class="input_btn_ele" type="submit" value="Submit" id="submit_id" /></div>'

statement+='</div>'

# close area
statement+='</form></div>'
statement+='<br /><br /><br /><br /><br /><br />'
statement+='</div></body></html>'

# style css
css_var='<style>'
 
css_var+='.inputs_side {left: -2%;float: right;clear: both;position: relative;  }'

css_var+='.input_element {display: inline-block;  }'

css_var+='.input_element > label {font-size: 20px;color: white;font-weight: bolder;  }'

css_var+='.input_tag {width: 24em;height: 2.5em;border-radius: 6px;border-width: 0;margin: 0 1em 0 0;  }'

css_var+='.input_item_div {margin: 2em 0;  }'

css_var+='.mine_form {position: relative;left: 3%;top: 14%;  }'

css_var+='.form_pack {background: #0f6b4c;width: 30em;height: 30em;position: relative;left: 30%;top: 4em;border-radius: 10px;  }'

css_var+='.btn_input {float: left;left: 12%;position: relative;margin: 0 20px;  }'

css_var+='.input_btn_ele {border-width: 0;border-radius: 7px;height: 3em;width: 5em;font-size: 18px;  }'

css_var+='.btn_div_items {position: relative;top: 1em;left: 2em;  }'

css_var+='</style>'

echo ${statement}${css_var} >> ${file_name}