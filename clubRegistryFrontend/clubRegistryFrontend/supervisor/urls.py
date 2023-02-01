from django.urls import path, include
from .views import *

app_name='supervisor'

urlpatterns = [
    path('club', ClubView.as_view(), name='club'),
    path('application', ApplicationView.as_view(), name='application'),
    path('application/add', AddApplicationView.as_view(), name='add_application'),
    path('club/<str:taxNo>/edit', EditClubView.as_view(), name='edit_club'),
    path('club/<str:taxNo>/delete', DeleteClubView, name='delete_club'),
    path('club/<str:taxNo>/shop/add', AddShopView.as_view(), name='add_shop'),
    path('club/<str:taxNo>/shop/<int:id>/edit', EditShopView.as_view(), name='edit_shop'),
    path('club/<str:taxNo>/shop/<int:id>/delete', DeleteShopView, name='delete_shop'),
]