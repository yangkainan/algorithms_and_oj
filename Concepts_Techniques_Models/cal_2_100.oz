declare
S={NewStore}

fun {M100 N Val}
   Res in

   {Put S N Val}

   if N > 64 then
      Res = {Get S 2} + {Get S 32} + {Get S 64}
   else 
      {M100 N*2 Val*Val}
   end 
   
end

{Browse {M100 1 2}}