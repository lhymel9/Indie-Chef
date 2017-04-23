####################################################################################
#
# FILE NAME:   urls.py
# 
# DESCRIPTION: This is a necessary file within Django's database framework.
#              In this file, urls associated with Indie Pantry are declared.
#              Additionally, it is also where urls associated with the REST
#              API are declared and routed.
#
# REFERENCES:  
#
#    DATE        BY      DESCRIPTION
# ==========  ========  ===============
#  04/06/17   Layne H.  Added routing for basic Vendor,Customer,MenuCategory,
#           		MenuItem,Sale,Review,ReviewFlag, and Advertising  APIs.
#  04/13/17   Layne H.  Added routing for vendors_all/specific and
#                       customers_all/specific
#  04/13/17   Layne H.  Removed routing, added APIViews as standard url patterns                                                                             
####################################################################################

from django.conf.urls import url,include
from django.contrib import admin
from rest_framework import routers
from IPapp import views

router = routers.DefaultRouter()
router.register(r'api/vendors',views.VendorViewSet)
router.register(r'api/customers',views.CustomerViewSet)
router.register(r'api/menucat',views.MenuCategoryViewSet)
router.register(r'api/menuitem',views.MenuItemViewSet)
router.register(r'api/sale',views.SaleViewSet)
router.register(r'api/review',views.ReviewViewSet)
router.register(r'api/reviewflag',views.ReviewFlagViewSet)
router.register(r'api/advertising',views.AdvertisingViewSet)
admin.autodiscover() 

urlpatterns = [
    url(r'^admin/', include(admin.site.urls)),
    url(r'^', include(router.urls)),
    url(r'^api-auth/', include('rest_framework.urls', namespace='rest_framework'))
]

