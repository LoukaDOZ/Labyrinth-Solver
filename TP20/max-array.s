        .equ SYS_EXIT, 1
	.equ SYS_READ, 3
	.equ SYS_WRITE, 4

	.arm
	.data

sz:	.word 7
ary:	.word 15,7,7,2,55,1,80

pad:	.space 256
	
	.text
	.globl _start

max:	push {r2,r3,r4,lr}
	ldr r2,[r0],#4
	mov r3,#1
	
loop:	cmp r3,r1
	bge end
	ldr r4,[r0],#4
	add r3,r3,#1
	cmp r2,r4
	movlt r2,r4
	b loop
	
end:	mov r0,r2
	pop {r2,r3,r4,pc}

_start:	ldr r0, =ary
	ldr r2, =sz
	ldr r1,[r2]
	bl max
	mov r1,r0
	ldr r0, =pad
	bl sprintfd
	mov r2, r1
	mov r1, r0
	mov r0, #1
	
	mov r7, #SYS_WRITE
	swi #0

	mov r7,#SYS_EXIT
	mov r0,#0
	swi #0x0
	
	.end

