        .equ SYS_EXIT, 1
	.equ SYS_READ, 3
	.equ SYS_WRITE, 4

	.arm
	.data

int1:	.word 0

strfin:	.asciz "\n"
str:	.space 256     @The address of the string to be displayed by write(1,..)

	.text
	.globl _start

_start:
	@ Load integers into your favorite registers

        ldr r3,=int1 
	ldr r2,[r3]   
	
			
	@ We are going to call sprintfd. So prepare arguments in r0,r1. The address of the memory
	@location to write to must be in r0:
	
	ldr r0,=str

	@ The value to convert must be found in r1
	mov r1,r2 

	@Call the function
	bl sprintfd  

	@ The address of the string to be displayed is in r0. Its length is in r1.
	@ So, prepare with the registers for SYS_WRITE:
	
	
        mov r2,r1
	mov r1,r0
	mov r7,#SYS_WRITE
	mov r0,#1
	swi #0x0  @ printf("%d"...) has been performed

	mov r7,#SYS_WRITE
	mov r0,#1
	ldr r1,=strfin
	mov r2,#1
        swi #0x0  @ printf("\n") has been performed
        
	
	mov r7,#SYS_EXIT
	mov r0,#0
	swi #0x0
	
	
	.end

