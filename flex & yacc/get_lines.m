function get_lines(DIR)

files=dir([DIR,'*.java']);
count=0;
for i=1 : length(files)
    inf=fopen([DIR, files(i).name]);
    
    while(1)
        a = fgetl(inf);
        if(~isstr(a))
            break;
        end
        
        count=count+1;
    end
        
    fclose(inf);
end

disp(count);

end