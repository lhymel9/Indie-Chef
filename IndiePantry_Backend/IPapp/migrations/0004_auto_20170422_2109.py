# -*- coding: utf-8 -*-
# Generated by Django 1.9.12 on 2017-04-23 02:09
from __future__ import unicode_literals

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('IPapp', '0003_auto_20170422_2103'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='advertising',
            name='vendor_fadvertising',
        ),
        migrations.RemoveField(
            model_name='menucategory',
            name='vendor_fmenucategory',
        ),
        migrations.RemoveField(
            model_name='menuitem',
            name='menu_fmenuitem',
        ),
        migrations.RemoveField(
            model_name='review',
            name='customer_freview',
        ),
        migrations.RemoveField(
            model_name='review',
            name='vendor_freview',
        ),
        migrations.RemoveField(
            model_name='reviewflag',
            name='review_freviewflag',
        ),
        migrations.RemoveField(
            model_name='sale',
            name='customer_fsale',
        ),
        migrations.RemoveField(
            model_name='sale',
            name='item_fsale',
        ),
        migrations.RemoveField(
            model_name='sale',
            name='menu_fsale',
        ),
        migrations.RemoveField(
            model_name='sale',
            name='vendor_fsale',
        ),
        migrations.DeleteModel(
            name='Advertising',
        ),
        migrations.DeleteModel(
            name='Customer',
        ),
        migrations.DeleteModel(
            name='MenuCategory',
        ),
        migrations.DeleteModel(
            name='MenuItem',
        ),
        migrations.DeleteModel(
            name='Review',
        ),
        migrations.DeleteModel(
            name='ReviewFlag',
        ),
        migrations.DeleteModel(
            name='Sale',
        ),
        migrations.DeleteModel(
            name='Vendor',
        ),
    ]
