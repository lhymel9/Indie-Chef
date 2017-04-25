####################################################################################
#
# FILE NAME:   views.py
# 
# DESCRIPTION: This is a necessary file within Django's database framework.
#              In this file, viewsets for the various serialized information
#              are established.  At the very least, there is one viewset for
#              each table in the database.
#
# REFERENCES:  serializers.py
#
#    DATE        BY      DESCRIPTION
# ==========  ========  ===============
#  04/06/17   Layne H.  Created the ViewSet for the Vendor table.
#  04/08/17   Layne H.  Created ViewSets for Customer, MenuCategory, MenuItem, Sale,
#           Advertising, Review, and ReviewFlag tables.
#  04/24/17   Layne H.  Added paypal column to Vendor table.
#                                                                               
####################################################################################

from django.shortcuts import render
from rest_framework import viewsets
from .models import *
from .serializers import *

class VendorViewSet(viewsets.ModelViewSet):
	queryset = Vendor.objects.all()
	serializer_class = VendorSerializer
 
class CustomerViewSet(viewsets.ModelViewSet):
	queryset = Customer.objects.all()
	serializer_class = CustomerSerializer
	
class MenuCategoryViewSet(viewsets.ModelViewSet):
	queryset = MenuCategory.objects.all()
	serializer_class = MenuCategorySerializer
 
class MenuItemViewSet(viewsets.ModelViewSet):
	queryset = MenuItem.objects.all()
	serializer_class = MenuItemSerializer
 
class SaleViewSet(viewsets.ModelViewSet):
	queryset = Sale.objects.all()
	serializer_class = SaleSerializer
 
class AdvertisingViewSet(viewsets.ModelViewSet):
	queryset = Advertising.objects.all()
	serializer_class = AdvertisingSerializer
 
class ReviewViewSet(viewsets.ModelViewSet):
	queryset = Review.objects.all()
	serializer_class = ReviewSerializer
 
class ReviewFlagViewSet(viewsets.ModelViewSet):
	queryset = ReviewFlag.objects.all()
	serializer_class = ReviewFlagSerializer
