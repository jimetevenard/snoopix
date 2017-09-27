<?xml version="1.0" encoding="UTF-8"?>
<sch:schema xmlns:sch="http://xml.ascc.net/schematron/" xmlns:xsi="http://www.w3.org/2000/10/XMLSchema-instance"
    xsi:schemaLocation="http://www.ascc.net/xml/schematron
    http://www.ascc.net/xml/schematron/schematron1-5.xsd" >
    <sch:ns uri="http://toto" prefix="toto"/>
    
    <sch:pattern >
        <sch:rule context="toto:toto">
            <sch:assert test="local-name(*[1]) = 'foo'">toto element must start with a foo element</sch:assert>
        </sch:rule>
    </sch:pattern>    
</sch:schema>