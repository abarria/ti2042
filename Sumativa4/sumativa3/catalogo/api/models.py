from ninja import Schema
from typing import List

class MessageSchema(Schema):
    message: str

class ProviderOutputSchema(Schema):
    fantasy_name: str
    tax_name: str
    tax_id: str
    enabled: bool

class ServiceInputSchema(Schema):
    nombre: str
    description: str
    provider: int
    price: int
    from_date: str
    thru_date: str

class AddressOutputSchema(Schema):
    provider: int
    type: str
    address1: str
    address2: str
    zipcode: str
    city: str
    region: str
    country: str

class ContactOutputSchema(Schema):
    provider: int
    type: str
    first_name: str
    last_name: str
    email: str
    phone: str
    mobile: str

class ServiceOutputSchema(Schema):
    nombre: str
    description: str
    provider: ProviderOutputSchema
    price: int
    from_date: str
    thru_date: str
    contacto: List[ContactOutputSchema]
    direccion: AddressOutputSchema






