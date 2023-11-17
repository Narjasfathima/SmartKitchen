from django.db import models

# Create your models here.
class LoginTb(models.Model):
    lid = models.AutoField(primary_key=True)
    username = models.CharField(max_length=25)
    password = models.CharField(max_length=25)
    ltype = models.CharField(max_length=10)

    class Meta:
        managed = False
        db_table = 'login_tb'
