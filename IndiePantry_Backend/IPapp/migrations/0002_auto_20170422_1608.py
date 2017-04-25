# -*- coding: utf-8 -*-
# Generated by Django 1.9.12 on 2017-04-22 21:08
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('IPapp', '0001_initial'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='customer',
            name='latC',
        ),
        migrations.RemoveField(
            model_name='customer',
            name='lonC',
        ),
        migrations.RemoveField(
            model_name='vendor',
            name='latV',
        ),
        migrations.RemoveField(
            model_name='vendor',
            name='lonV',
        ),
        migrations.AddField(
            model_name='customer',
            name='addressC',
            field=models.CharField(default=555, max_length=60),
            preserve_default=False,
        ),
        migrations.AddField(
            model_name='vendor',
            name='addressV',
            field=models.CharField(default=555, max_length=60),
            preserve_default=False,
        ),
    ]