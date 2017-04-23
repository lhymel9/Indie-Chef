####################################################################################
#
# FILE NAME:   admin.py
# 
# DESCRIPTION: This is a necessary file within Django's database framework.
#              In this file, tables of the database are registered into
#              django's built in admin site.
#
# REFERENCES:  
#
#    DATE        BY      DESCRIPTION
# ==========  ========  ===============
#  04/09/17   Layne H.  Added Vendor admin registration.
#  04/12/17   Layne H.  Added all other tables.                     
#                                                                               
####################################################################################

from django.contrib import admin
from .models import *

admin.site.register(Vendor)
admin.site.register(Customer)
admin.site.register(MenuCategory)
admin.site.register(MenuItem)
admin.site.register(Advertising)
admin.site.register(Review)
admin.site.register(ReviewFlag)
admin.site.register(Sale)
