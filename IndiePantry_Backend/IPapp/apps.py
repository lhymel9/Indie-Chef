####################################################################################
#
# FILE NAME:   apps.py
# 
# DESCRIPTION: This is a necessary file within Django's database framework.
#              In this file, all applications related to the project are
#              declared (along with their configuration files.  
#              Additionally, each application declared here are added to the list
#              of applications in settings.py.
#
# REFERENCES:  settings.py, ../
#
#    DATE        BY      DESCRIPTION
# ==========  ========  ===============
#  04/07/17   Layne H.  Added IPapp.
#                                                                               
####################################################################################

from django.apps import AppConfig

class IpappConfig(AppConfig):
    name = 'IPapp'
