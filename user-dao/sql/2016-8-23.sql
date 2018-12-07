CREATE TABLE serverroom.ldap_user                                                    
(
employeeId                     character varying(20)  NOT NULL,
objectClass                    character varying(200),
cn                             character varying(200),
sn                             character varying(200),
ou                             character varying(200),
dc                             character varying(200),
o                              character varying(200),
c                              character varying(200),
co                             character varying(200),
company                        character varying(200),
telephoneNumber                character varying(200),
distinguishedName              character varying(200),
mail                           character varying(200),
locationId                     character varying(200),
name                           character varying(200),
employeeType	                  character varying(200),
givenName	                    character varying(200),
hpCrossCompanyManager	        character varying(200),
hpCrossCompanyManagerID	      character varying(20),
st	                            character varying(200),
department	                    character varying(200),
displayName	                  character varying(200),
postalCode	                    character varying(200),
streetAddress	                character varying(200),
          
   CONSTRAINT                      pk_ldap_user primary key (employeeId) 
)                                                                         
WITH (                                                                    
  OIDS=FALSE                                                              
);                                                                        
ALTER TABLE serverroom.LDAP_User                                                     
  OWNER TO postgres; 