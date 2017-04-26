###################################################################################
#
# FILE NAME:   models.py
# 
# DESCRIPTION: This is a necessary file within Django's database framework.
#              In this file, all tables and their associated columns are
#              detailed in terms of Python 'classes'.  The name of each
#              class corresponds to the name of a table in the database.
#              Each variable within a respective class respresents a
#              specfic column in the table detailed by the class.
#
# REFERENCES:  Software System Design Document - Page 10
#
#    DATE        BY      DESCRIPTION
# ==========  ========  ===============
#  04/06/17   Layne H.  Added models for each database table.
#  04/22/17   Layne H.  Removed lat and lon columns for Vendor and Customer.
#                       Added address columns for Vendor and Customer.               
#  04/24/17   Layne H.  Added paypal column to Vendor table
#  04/25/17   Layne H.  Readded lat and lon for Vendor and Customer.
#                       Removed address fields.
#  04/25/17   Layne H.  Sale Changes: Added item series char field, 
#                       removed menu and item foreign keys.
#                                                                
####################################################################################
 
from django.db import models

class Vendor(models.Model):
        latV = models.CharField(max_length=8)
        lonV = models.CharField(max_length=8)
        emailV = models.CharField(max_length=40)
        passwordV = models.CharField(max_length=40)
        phone = models.CharField(max_length=40)
        paypal = models.CharField(max_length=40)
        rating = models.CharField(max_length=4)
        nameV = models.CharField(max_length=40)
        approved = models.BooleanField()
 
class MenuCategory(models.Model):
        category = models.CharField(max_length=40)
        size = models.IntegerField()
        vendor_fmenucategory = models.ForeignKey(Vendor,on_delete=models.CASCADE)
 
class MenuItem(models.Model):
        cost = models.FloatField()
        iType = models.CharField(max_length=40)
        description = models.CharField(max_length=140)
        menu_fmenuitem = models.ForeignKey(MenuCategory,on_delete=models.CASCADE)
 
class Customer(models.Model):
        username = models.CharField(max_length=40)
        passwordC = models.CharField(max_length=40)
        nameC = models.CharField(max_length=40)
        latC = models.CharField(max_length=8)
        lonC = models.CharField(max_length=8)
        emailC = models.CharField(max_length=40)
 
class Sale(models.Model):
        date = models.DateTimeField('auto_now_add=True')
        series = models.CharField(max_length=350)
        subtotal = models.CharField(max_length=10)
        total = models.CharField(max_length=10)
        tax = models.CharField(max_length=10)
        customer_fsale = models.ForeignKey(Customer,on_delete=models.CASCADE)
        vendor_fsale = models.ForeignKey(Vendor,on_delete=models.CASCADE)
 
class Review(models.Model):
        text = models.CharField(max_length=140)
        rate = models.IntegerField()
        vendor_freview = models.ForeignKey(Vendor,on_delete=models.CASCADE)
        customer_freview = models.ForeignKey(Customer,on_delete=models.CASCADE)
 
class ReviewFlag(models.Model):
        reason = models.CharField(max_length=140)
        review_freviewflag = models.ForeignKey(Review,on_delete=models.CASCADE)
 
class Advertising(models.Model):
        start = models.DateTimeField('auto_now_add=True')
        vendor_fadvertising = models.ForeignKey(Vendor,on_delete=models.CASCADE)
