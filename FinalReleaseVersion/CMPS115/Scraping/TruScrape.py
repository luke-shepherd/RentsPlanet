from bs4 import BeautifulSoup
import re
import string
import sys
import mechanize

def selt(form):
	return form.attrs.get('id', None) == 'searchbox_form'

def getTags(soupObj):
	bath_found = False
	bed_found = False
	sqft_found = False
	info_list = []
	
	for tag in soupObj.find_all('li'):
		tag_body = unicode(tag.string)
		
		if len(tag_body) > 200: 
			continue
		
		if (unicode("Bathrooms") in tag_body or unicode("Bathroom") in tag_body) and not bath_found:
			info_list.append(tag_body)
			bath_found = True

			
		if (unicode("Bedrooms") in tag_body or unicode("Bedroom") in tag_body) and not bed_found:
			info_list.append(tag_body)
			bed_found = True

		if unicode('sqft') in tag_body and not sqft_found:
			info_list.append(tag_body)
			sqft_found = True

		if len(info_list) >= 3:
			break
	
	return info_list


def runProg():
	in_file = open('/root/Scraping/addr_file.in', 'r')
	addr = in_file.readline()
	in_file.close()

	trUrl = 'https://www.trulia.com/'

	br = mechanize.Browser()
	br.open(trUrl)

	br.select_form(predicate=selt)
	br.form['search'] = addr
	br.submit()

	soup = BeautifulSoup(br.response().read(), 'html.parser')
	inf_list = getTags(soup)
	out_file = open('/root/Scraping/addr_file.out', 'w+')

	if len(inf_list) > 0:
		for item in inf_list:
			out_file.write(item + '\n')
	out_file.close()

runProg()
